package com.miun.martinclass.demo.scheduling.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shifts")
public class Shift {

    public enum ShiftType {
        AFTERNOON,
        NIGHT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "shift_type", nullable = false)
    private ShiftType shiftType;

    // Default constructor required by JPA
    public Shift() {}

    // Constructor for convenience
    public Shift(Long employeeId, LocalDate date, ShiftType shiftType) {
        this.employeeId = employeeId;
        this.date = date;
        this.shiftType = shiftType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }
}