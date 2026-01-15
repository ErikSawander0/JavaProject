package com.miun.martinclass.demo.scheduling.api;

import com.miun.martinclass.demo.scheduling.entity.Employee;
import com.miun.martinclass.demo.scheduling.entity.Shift;
import com.miun.martinclass.demo.scheduling.entity.ShiftChangeRequest;
import com.miun.martinclass.demo.scheduling.entity.ShiftChangeRequest.RequestStatus;
import com.miun.martinclass.demo.scheduling.service.EmployeeService;
import com.miun.martinclass.demo.scheduling.service.ShiftService;
import com.miun.martinclass.demo.scheduling.service.ShiftRequestService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/shift-requests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShiftRequestApi {

    @Inject
    ShiftRequestService shiftRequestService;

    @Inject
    EmployeeService employeeService;

    @Inject
    ShiftService shiftService;

    /**
     * GET /api/shift-requests
     * Query params: ?targetEmployeeId=X (optional), ?status=PENDING|ACCEPTED|REJECTED (optional)
     * Returns list of shift change requests with full details
     */
    @GET
    public Response getRequests(
            @QueryParam("targetEmployeeId") Long targetEmployeeId,
            @QueryParam("status") String statusStr) {

        RequestStatus status = null;
        if (statusStr != null && !statusStr.isEmpty()) {
            try {
                status = RequestStatus.valueOf(statusStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Invalid status. Use PENDING, ACCEPTED, or REJECTED"))
                        .build();
            }
        }

        List<ShiftChangeRequest> requests = shiftRequestService.getRequestsFiltered(targetEmployeeId, status);

        List<ShiftRequestDTO> response = requests.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return Response.ok(response).build();
    }

    /**
     * GET /api/shift-requests/{id}
     * Returns a single request by ID with full details
     */
    @GET
    @Path("/{id}")
    public Response getRequestById(@PathParam("id") Long id) {
        ShiftChangeRequest request = shiftRequestService.findById(id);

        if (request == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Request not found"))
                    .build();
        }

        return Response.ok(toDTO(request)).build();
    }

    /**
     * POST /api/shift-requests
     * Create a new shift change request
     * Request body: { "requesterId": int, "targetEmployeeId": int, "shiftToGiveId": int|null, "shiftToTakeId": int|null }
     */
    @POST
    public Response createRequest(CreateRequestBody body) {
        if (body == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Request body is required"))
                    .build();
        }

        if (body.requesterId == null || body.targetEmployeeId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("requesterId and targetEmployeeId are required"))
                    .build();
        }

        if (body.shiftToGiveId == null && body.shiftToTakeId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("At least one of shiftToGiveId or shiftToTakeId is required"))
                    .build();
        }

        // Validate employees exist
        if (employeeService.findById(body.requesterId) == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Requester employee not found"))
                    .build();
        }
        if (employeeService.findById(body.targetEmployeeId) == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Target employee not found"))
                    .build();
        }

        // Validate shifts exist if provided
        if (body.shiftToGiveId != null && shiftService.findById(body.shiftToGiveId) == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Shift to give not found"))
                    .build();
        }
        if (body.shiftToTakeId != null && shiftService.findById(body.shiftToTakeId) == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Shift to take not found"))
                    .build();
        }

        ShiftChangeRequest request = new ShiftChangeRequest(
                body.requesterId,
                body.targetEmployeeId,
                body.shiftToGiveId,
                body.shiftToTakeId
        );

        // Check for conflicts before creating
        String conflictError = shiftRequestService.validateNoConflicts(request);
        if (conflictError != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(conflictError))
                    .build();
        }

        ShiftChangeRequest created = shiftRequestService.createRequest(request);
        if (created == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Failed to create request due to scheduling conflict"))
                    .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity(toDTO(created))
                .build();
    }

    /**
     * PUT /api/shift-requests/{id}
     * Update request status (accept or reject)
     * Request body: { "status": "ACCEPTED" | "REJECTED" }
     */
    @PUT
    @Path("/{id}")
    public Response updateRequestStatus(@PathParam("id") Long id, UpdateStatusBody body) {
        if (body == null || body.status == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Status is required"))
                    .build();
        }

        RequestStatus newStatus;
        try {
            newStatus = RequestStatus.valueOf(body.status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Invalid status. Use ACCEPTED or REJECTED"))
                    .build();
        }

        String error;
        if (newStatus == RequestStatus.ACCEPTED) {
            error = shiftRequestService.acceptRequest(id);
        } else if (newStatus == RequestStatus.REJECTED) {
            error = shiftRequestService.rejectRequest(id);
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Can only update to ACCEPTED or REJECTED"))
                    .build();
        }

        if (error != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(error))
                    .build();
        }

        ShiftChangeRequest updated = shiftRequestService.findById(id);
        return Response.ok(toDTO(updated)).build();
    }

    /**
     * DELETE /api/shift-requests/{id}
     * Cancel/delete a pending request
     */
    @DELETE
    @Path("/{id}")
    public Response deleteRequest(@PathParam("id") Long id) {
        ShiftChangeRequest request = shiftRequestService.findById(id);

        if (request == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Request not found"))
                    .build();
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Can only delete pending requests"))
                    .build();
        }

        shiftRequestService.deleteRequest(id);
        return Response.noContent().build();
    }

    // ========== Helper Methods ==========

    /**
     * Convert entity to DTO with all related data (employee names, shift details)
     */
    private ShiftRequestDTO toDTO(ShiftChangeRequest request) {
        ShiftRequestDTO dto = new ShiftRequestDTO();
        dto.id = request.getId();
        dto.requesterId = request.getRequesterId();
        dto.targetEmployeeId = request.getTargetEmployeeId();
        dto.shiftToGiveId = request.getShiftToGiveId();
        dto.shiftToTakeId = request.getShiftToTakeId();
        dto.status = request.getStatus().name();
        dto.createdAt = request.getCreatedAt().toString();

        // Add employee names
        Employee requester = employeeService.findById(request.getRequesterId());
        if (requester != null) {
            dto.requesterName = requester.getName();
        }

        Employee target = employeeService.findById(request.getTargetEmployeeId());
        if (target != null) {
            dto.targetEmployeeName = target.getName();
        }

        // Add shift details
        if (request.getShiftToGiveId() != null) {
            Shift shift = shiftService.findById(request.getShiftToGiveId());
            if (shift != null) {
                dto.shiftToGive = new ShiftDTO(shift.getId(), shift.getEmployeeId(), shift.getDate().toString(), shift.getShiftType().name());
            }
        }

        if (request.getShiftToTakeId() != null) {
            Shift shift = shiftService.findById(request.getShiftToTakeId());
            if (shift != null) {
                dto.shiftToTake = new ShiftDTO(shift.getId(), shift.getEmployeeId(), shift.getDate().toString(), shift.getShiftType().name());
            }
        }

        return dto;
    }

    // ========== DTOs ==========

    public static class CreateRequestBody {
        public Long requesterId;
        public Long targetEmployeeId;
        public Long shiftToGiveId;
        public Long shiftToTakeId;
    }

    public static class UpdateStatusBody {
        public String status;
    }

    public static class ShiftRequestDTO {
        public Long id;
        public Long requesterId;
        public Long targetEmployeeId;
        public Long shiftToGiveId;
        public Long shiftToTakeId;
        public String status;
        public String createdAt;
        public String requesterName;
        public String targetEmployeeName;
        public ShiftDTO shiftToGive;
        public ShiftDTO shiftToTake;
    }

    public static class ShiftDTO {
        public Long id;
        public Long employeeId;
        public String date;
        public String shiftType;

        public ShiftDTO(Long id, Long employeeId, String date, String shiftType) {
            this.id = id;
            this.employeeId = employeeId;
            this.date = date;
            this.shiftType = shiftType;
        }
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}