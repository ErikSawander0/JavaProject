package com.miun.martinclass.demo.OrderInfo.API;


import com.miun.martinclass.demo.OrderInfo.service.OrderService;
import com.miun.martinclass.demo.OrderInfo.entity.OrderGroup;
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
    public Response capture() {

        long id = service.saveOrder(new OrderGroup());

        return Response
                .ok(id)
                .build();
    }

    @PUT
    @Path("/{id}/reset")
    public Response resetDoneStatus(@PathParam("id") Long id) {
        service.markOrderasDone(id);
        return Response.ok().build();
    }
}

