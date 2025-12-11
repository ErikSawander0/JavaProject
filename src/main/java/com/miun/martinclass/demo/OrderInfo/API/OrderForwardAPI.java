package com.miun.martinclass.demo.OrderInfo.API;

import com.miun.martinclass.demo.OrderInfo.service.OrderService;
import com.miun.martinclass.demo.OrderInfo.entity.OrderGroup;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/forwardsTo")
public class OrderForwardAPI {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForwardedOrders() {

        List<OrderGroup> forwardedOrders = orderService.getActiveOrders();

        return Response.ok(forwardedOrders).build();
    }
    @GET
    @Path("/{id}/isDone")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkIfDone(@PathParam("id") Long id) {
        try {
            boolean done = orderService.isOrderDone(id);
            return Response.ok("{\"isDone\":" + done + "}").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .build();
        }
    }

}