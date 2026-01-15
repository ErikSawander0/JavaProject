package com.miun.martinclass.demo.scheduling.service;

import com.miun.martinclass.demo.scheduling.entity.Shift;
import com.miun.martinclass.demo.scheduling.entity.ShiftChangeRequest;
import com.miun.martinclass.demo.scheduling.entity.ShiftChangeRequest.RequestStatus;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ShiftRequestService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @EJB
    private ShiftService shiftService;

    // ========== ShiftChangeRequest CRUD ==========

    /**
     * Get all shift change requests
     */
    public List<ShiftChangeRequest> getAllRequests() {
        return em.createQuery("SELECT r FROM ShiftChangeRequest r", ShiftChangeRequest.class).getResultList();
    }

    /**
     * Find request by ID
     */
    public ShiftChangeRequest findById(Long id) {
        return em.find(ShiftChangeRequest.class, id);
    }

    /**
     * Create a new shift change request
     * Returns null if there's a scheduling conflict
     */
    public ShiftChangeRequest createRequest(ShiftChangeRequest request) {
        // Validate no scheduling conflicts before creating
        String conflictError = validateNoConflicts(request);
        if (conflictError != null) {
            return null; // Conflict detected
        }

        em.persist(request);
        return request;
    }

    /**
     * Update an existing request
     */
    public void updateRequest(ShiftChangeRequest request) {
        em.merge(request);
    }

    /**
     * Delete a request
     */
    public void deleteRequest(Long id) {
        ShiftChangeRequest request = em.find(ShiftChangeRequest.class, id);
        if (request != null) {
            em.remove(request);
        }
    }

    // ========== Query Methods ==========

    /**
     * Get pending requests where employee is the target (requests they need to respond to)
     */
    public List<ShiftChangeRequest> getPendingRequestsForEmployee(Long targetEmployeeId) {
        return em.createQuery(
                        "SELECT r FROM ShiftChangeRequest r WHERE r.targetEmployeeId = :targetId AND r.status = :status",
                        ShiftChangeRequest.class)
                .setParameter("targetId", targetEmployeeId)
                .setParameter("status", RequestStatus.PENDING)
                .getResultList();
    }

    /**
     * Get requests filtered by target employee and/or status
     */
    public List<ShiftChangeRequest> getRequestsFiltered(Long targetEmployeeId, RequestStatus status) {
        if (targetEmployeeId != null && status != null) {
            return em.createQuery(
                            "SELECT r FROM ShiftChangeRequest r WHERE r.targetEmployeeId = :targetId AND r.status = :status",
                            ShiftChangeRequest.class)
                    .setParameter("targetId", targetEmployeeId)
                    .setParameter("status", status)
                    .getResultList();
        } else if (targetEmployeeId != null) {
            return em.createQuery(
                            "SELECT r FROM ShiftChangeRequest r WHERE r.targetEmployeeId = :targetId",
                            ShiftChangeRequest.class)
                    .setParameter("targetId", targetEmployeeId)
                    .getResultList();
        } else if (status != null) {
            return em.createQuery(
                            "SELECT r FROM ShiftChangeRequest r WHERE r.status = :status",
                            ShiftChangeRequest.class)
                    .setParameter("status", status)
                    .getResultList();
        } else {
            return getAllRequests();
        }
    }

    // ========== Business Logic ==========

    /**
     * Validate that a shift change request won't create scheduling conflicts
     * Returns error message if conflict found, null if OK
     *
     * Rule: Employee cannot work the same shift TYPE on the same date twice
     * (e.g., can work Monday Afternoon AND Monday Night, but NOT two Monday Afternoons)
     */
    public String validateNoConflicts(ShiftChangeRequest request) {
        // If giving away a shift, check target employee doesn't already have that shift type on that date
        if (request.getShiftToGiveId() != null) {
            Shift shiftToGive = shiftService.findById(request.getShiftToGiveId());
            if (shiftToGive != null) {
                if (shiftService.employeeHasShiftOnDateAndType(
                        request.getTargetEmployeeId(),
                        shiftToGive.getDate(),
                        shiftToGive.getShiftType())) {
                    return "Scheduling conflict: Target employee already has a " +
                            shiftToGive.getShiftType() + " shift on " + shiftToGive.getDate();
                }
            }
        }

        // If taking a shift, check requester doesn't already have that shift type on that date
        if (request.getShiftToTakeId() != null) {
            Shift shiftToTake = shiftService.findById(request.getShiftToTakeId());
            if (shiftToTake != null) {
                if (shiftService.employeeHasShiftOnDateAndType(
                        request.getRequesterId(),
                        shiftToTake.getDate(),
                        shiftToTake.getShiftType())) {
                    return "Scheduling conflict: Requester already has a " +
                            shiftToTake.getShiftType() + " shift on " + shiftToTake.getDate();
                }
            }
        }

        return null; // No conflicts
    }

    /**
     * Accept a shift change request - performs the actual shift swap/transfer
     * Returns error message if conflict found, null if successful
     */
    public String acceptRequest(Long requestId) {
        ShiftChangeRequest request = findById(requestId);
        if (request == null) {
            return "Request not found";
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return "Request is no longer pending";
        }

        // Re-validate conflicts before accepting (in case shifts changed)
        String conflictError = validateNoConflicts(request);
        if (conflictError != null) {
            return conflictError;
        }

        // Perform the shift transfers
        if (request.getShiftToGiveId() != null) {
            // Transfer shift from requester to target
            Shift shift = shiftService.findById(request.getShiftToGiveId());
            if (shift != null) {
                shift.setEmployeeId(request.getTargetEmployeeId());
                shiftService.updateShift(shift);
            }
        }

        if (request.getShiftToTakeId() != null) {
            // Transfer shift from target to requester
            Shift shift = shiftService.findById(request.getShiftToTakeId());
            if (shift != null) {
                shift.setEmployeeId(request.getRequesterId());
                shiftService.updateShift(shift);
            }
        }

        // Update request status
        request.setStatus(RequestStatus.ACCEPTED);
        updateRequest(request);

        return null; // Success
    }

    /**
     * Reject a shift change request
     */
    public String rejectRequest(Long requestId) {
        ShiftChangeRequest request = findById(requestId);
        if (request == null) {
            return "Request not found";
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return "Request is no longer pending";
        }

        request.setStatus(RequestStatus.REJECTED);
        updateRequest(request);

        return null; // Success
    }
}