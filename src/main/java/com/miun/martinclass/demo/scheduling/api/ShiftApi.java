package com.miun.martinclass.demo.scheduling.api;

import com.miun.martinclass.demo.scheduling.entity.Shift;
import com.miun.martinclass.demo.scheduling.service.ShiftService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/shifts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShiftApi {

    @Inject
    ShiftService shiftService;

    /**
     * GET /api/shifts
     * Query params: ?date=YYYY-MM-DD (optional), ?employeeId=X (optional)
     * Returns list of shifts, optionally filtered
     */
    @GET
    public Response getShifts(
            @QueryParam("date") String dateStr,
            @QueryParam("employeeId") Long employeeId) {

        LocalDate date = null;
        if (dateStr != null && !dateStr.isEmpty()) {
            try {
                date = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Invalid date format. Use YYYY-MM-DD"))
                        .build();
            }
        }

        List<Shift> shifts = shiftService.getShiftsFiltered(date, employeeId);

        List<ShiftDTO> response = shifts.stream()
                .map(s -> new ShiftDTO(s.getId(), s.getEmployeeId(), s.getDate().toString(), s.getShiftType().name()))
                .collect(Collectors.toList());

        return Response.ok(response).build();
    }

    /**
     * GET /api/shifts/{id}
     * Returns a single shift by ID
     */
    @GET
    @Path("/{id}")
    public Response getShiftById(@PathParam("id") Long id) {
        Shift shift = shiftService.findById(id);

        if (shift == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Shift not found"))
                    .build();
        }

        ShiftDTO response = new ShiftDTO(shift.getId(), shift.getEmployeeId(), shift.getDate().toString(), shift.getShiftType().name());
        return Response.ok(response).build();
    }

    // ========== DTOs ==========

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