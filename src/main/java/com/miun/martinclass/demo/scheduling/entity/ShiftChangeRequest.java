package com.miun.martinclass.demo.scheduling.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shift_change_requests")
public class ShiftChangeRequest {

    public enum RequestStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requester_id", nullable = false)
    private Long requesterId;

    @Column(name = "target_employee_id", nullable = false)
    private Long targetEmployeeId;

    // Nullable - null if just taking a shift
    @Column(name = "shift_to_give_id")
    private Long shiftToGiveId;

    // Nullable - null if just giving away a shift
    @Column(name = "shift_to_take_id")
    private Long shiftToTakeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Default constructor required by JPA
    public ShiftChangeRequest() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor for convenience
    public ShiftChangeRequest(Long requesterId, Long targetEmployeeId, Long shiftToGiveId, Long shiftToTakeId) {
        this.requesterId = requesterId;
        this.targetEmployeeId = targetEmployeeId;
        this.shiftToGiveId = shiftToGiveId;
        this.shiftToTakeId = shiftToTakeId;
        this.status = RequestStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getTargetEmployeeId() {
        return targetEmployeeId;
    }

    public void setTargetEmployeeId(Long targetEmployeeId) {
        this.targetEmployeeId = targetEmployeeId;
    }

    public Long getShiftToGiveId() {
        return shiftToGiveId;
    }

    public void setShiftToGiveId(Long shiftToGiveId) {
        this.shiftToGiveId = shiftToGiveId;
    }

    public Long getShiftToTakeId() {
        return shiftToTakeId;
    }

    public void setShiftToTakeId(Long shiftToTakeId) {
        this.shiftToTakeId = shiftToTakeId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}