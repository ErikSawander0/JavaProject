package com.miun.martinclass.demo.OrderInfo;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/orders")

public class OrderCaptureAPI
{

    @Inject
    OrderService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response capture(OrderGroup body) {

        service.saveOrder(body);

        return Response.accepted().build();
    }

}

