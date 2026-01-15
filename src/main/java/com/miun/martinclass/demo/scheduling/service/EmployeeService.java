package com.miun.martinclass.demo.scheduling.service;

import com.miun.martinclass.demo.scheduling.entity.Employee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.NoResultException;

import java.util.List;

@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    // ========== Employee CRUD ==========

    /**
     * Get all employees
     */
    public List<Employee> getAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    /**
     * Find employee by ID
     */
    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    /**
     * Find employee by username
     */
    public Employee findByUsername(String username) {
        try {
            return em.createQuery(
                            "SELECT e FROM Employee e WHERE e.username = :username",
                            Employee.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Authenticate employee - returns employee if credentials match, null otherwise
     */
    public Employee authenticate(String username, String password) {
        Employee employee = findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    /**
     * Create a new employee
     */
    public void createEmployee(Employee employee) {
        em.persist(employee);
    }

    /**
     * Update an existing employee
     */
    public void updateEmployee(Employee employee) {
        em.merge(employee);
    }

    /**
     * Delete an employee
     */
    public void deleteEmployee(Long id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            em.remove(employee);
        }
    }
}