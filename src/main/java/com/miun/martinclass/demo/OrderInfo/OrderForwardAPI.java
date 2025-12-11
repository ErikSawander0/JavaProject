package com.miun.martinclass.demo.OrderInfo;

import com.miun.martinclass.demo.bookings.entity.Booking;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/forwardsTo")
public class OrderForwardAPI {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForwardedOrders() {

        List<OrderGroup> forwardedOrders = orderService.getOrders();

        return Response.ok(forwardedOrders).build();
    }
}