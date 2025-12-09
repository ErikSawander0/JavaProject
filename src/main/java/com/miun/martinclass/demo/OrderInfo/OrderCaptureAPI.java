package com.miun.martinclass.demo.OrderInfo;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")

public class OrderCaptureAPI {
    @Inject
    OrderService orderService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response captureOrders(OrderGroup orderGroup) {
        if (orderGroup == null || orderGroup.getOrders() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        orderService.saveOrder(orderGroup);

        return Response.ok().build();
    }

}
