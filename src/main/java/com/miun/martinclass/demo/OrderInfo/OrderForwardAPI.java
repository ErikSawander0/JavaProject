package com.miun.martinclass.demo.OrderInfo;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/forwardsTo")

public class OrderForwardAPI {
    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response spotlight(){
        List<OrderGroup> infoBuffer = orderService.getOrders();

        return Response.ok(infoBuffer).build();
    }

}
