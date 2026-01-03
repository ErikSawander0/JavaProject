package com.miun.martinclass.demo.OrderInfo.API;

import com.miun.martinclass.demo.OrderInfo.service.OrderService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/groups")
public class GroupAPI {

    @Inject
    OrderService service;

    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveGroupIds() {
        List<Long> groupIds = service.findActiveGroupIds();
        return Response.ok(groupIds).build();
    }
}
