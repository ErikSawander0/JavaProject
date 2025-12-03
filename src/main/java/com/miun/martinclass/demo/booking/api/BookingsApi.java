package com.miun.martinclass.demo.booking.api;

import com.miun.martinclass.demo.booking.service.BookingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
public class BookingsApi {

    @Inject
    BookingService bookingService;

    @GET
    public Response getBooked(@QueryParam("date") String date) {
        return Response.ok(
                bookingService.getBookedTimes(date)
        ).build();
    }
}
