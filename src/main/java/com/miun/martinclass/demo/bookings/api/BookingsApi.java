package com.miun.martinclass.demo.bookings.api;

import com.miun.martinclass.demo.bookings.service.BookingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/getTodaysBookings")
@Produces(MediaType.APPLICATION_JSON)

public class BookingsApi {

    @Inject
    BookingService bookingService;

    @GET
    public Response getBooked(@QueryParam("date") String date) {
        return Response.ok(
                bookingService.getTodaysBookings()
        ).build();
    }
}