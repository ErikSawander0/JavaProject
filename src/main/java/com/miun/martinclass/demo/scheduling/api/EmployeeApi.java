package com.miun.martinclass.demo.scheduling.api;

import com.miun.martinclass.demo.scheduling.entity.Employee;
import com.miun.martinclass.demo.scheduling.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeApi {

    @Inject
    EmployeeService employeeService;

    /**
     * GET /api/employees
     * Returns list of all employees (without passwords)
     */
    @GET
    public Response getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        // Map to DTO to exclude password
        List<EmployeeDTO> response = employees.stream()
                .map(e -> new EmployeeDTO(e.getId(), e.getUsername(), e.getName()))
                .collect(Collectors.toList());

        return Response.ok(response).build();
    }

    /**
     * GET /api/employees/{id}
     * Returns a single employee by ID
     */
    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Employee not found"))
                    .build();
        }

        EmployeeDTO response = new EmployeeDTO(employee.getId(), employee.getUsername(), employee.getName());
        return Response.ok(response).build();
    }

    // ========== DTOs ==========

    public static class EmployeeDTO {
        public Long id;
        public String username;
        public String name;

        public EmployeeDTO(Long id, String username, String name) {
            this.id = id;
            this.username = username;
            this.name = name;
        }
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}