package com.miun.martinclass.demo.admin;

import com.miun.martinclass.demo.scheduling.entity.Employee;
import com.miun.martinclass.demo.scheduling.entity.Shift;
import com.miun.martinclass.demo.scheduling.service.EmployeeService;
import com.miun.martinclass.demo.scheduling.service.ShiftService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named("schedulingAdminBean")
@ViewScoped
public class SchedulingAdminBean implements Serializable {

    @Inject
    private EmployeeService employeeService;

    @Inject
    private ShiftService shiftService;

    // Lists
    private List<Employee> employees;
    private List<Shift> shifts;

    // New objects for creation
    private Employee newEmployee = new Employee();
    private Shift newShift = new Shift();

    // Selected IDs for dropdowns
    private Long selectedEmployeeId;

    // Filter for shifts
    private LocalDate filterDate;
    private Long filterEmployeeId;

    @PostConstruct
    public void init() {
        reloadData();
    }

    private void reloadData() {
        employees = employeeService.getAllEmployees();
        loadShifts();
    }

    private void loadShifts() {
        shifts = shiftService.getShiftsFiltered(filterDate, filterEmployeeId);
    }

    // ========== Employee CRUD ==========

    public void addEmployee() {
        // Check if username already exists
        if (employeeService.findByUsername(newEmployee.getUsername()) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Användarnamnet finns redan", null));
            return;
        }
        employeeService.createEmployee(newEmployee);
        newEmployee = new Employee();
        reloadData();
    }

    public void updateEmployee(Employee employee) {
        employeeService.updateEmployee(employee);
        reloadData();
    }

    public void removeEmployee(Employee employee) {
        // First delete all shifts for this employee
        List<Shift> employeeShifts = shiftService.getShiftsByEmployeeId(employee.getId());
        for (Shift shift : employeeShifts) {
            shiftService.deleteShift(shift.getId());
        }
        employeeService.deleteEmployee(employee.getId());
        reloadData();
    }

    // ========== Shift CRUD ==========

    public void addShift() {
        if (selectedEmployeeId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Välj en anställd", null));
            return;
        }

        // Check for conflicts
        if (shiftService.employeeHasShiftOnDateAndType(
                selectedEmployeeId, newShift.getDate(), newShift.getShiftType())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Anställd har redan ett pass av denna typ på detta datum", null));
            return;
        }

        newShift.setEmployeeId(selectedEmployeeId);
        shiftService.createShift(newShift);
        newShift = new Shift();
        selectedEmployeeId = null;
        reloadData();
    }

    public void removeShift(Shift shift) {
        shiftService.deleteShift(shift.getId());
        reloadData();
    }

    public void applyFilter() {
        loadShifts();
    }

    public void clearFilter() {
        filterDate = null;
        filterEmployeeId = null;
        loadShifts();
    }

    // Helper method to get employee name by ID
    public String getEmployeeName(Long employeeId) {
        if (employeeId == null) return "–";
        Employee emp = employeeService.findById(employeeId);
        return emp != null ? emp.getName() : "Okänd";
    }

    // Helper to display shift type in Swedish
    public String getShiftTypeLabel(Shift.ShiftType type) {
        if (type == null) return "–";
        return switch (type) {
            case AFTERNOON -> "Eftermiddag";
            case NIGHT -> "Natt";
        };
    }

    // ========== Getters and Setters ==========

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public Shift getNewShift() {
        return newShift;
    }

    public void setNewShift(Shift newShift) {
        this.newShift = newShift;
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public LocalDate getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(LocalDate filterDate) {
        this.filterDate = filterDate;
    }

    public Long getFilterEmployeeId() {
        return filterEmployeeId;
    }

    public void setFilterEmployeeId(Long filterEmployeeId) {
        this.filterEmployeeId = filterEmployeeId;
    }

    // Get shift types for dropdown
    public Shift.ShiftType[] getShiftTypes() {
        return Shift.ShiftType.values();
    }
}