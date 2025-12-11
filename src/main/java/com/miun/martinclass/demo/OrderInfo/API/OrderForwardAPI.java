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
}