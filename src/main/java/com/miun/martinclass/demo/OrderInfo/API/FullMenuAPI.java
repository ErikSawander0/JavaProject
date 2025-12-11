package com.miun.martinclass.demo.OrderInfo.API;

import com.miun.martinclass.demo.OrderInfo.entity.CarteMenuItem;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/carte-menu")
public class FullMenuAPI {

    @Inject
    private MenuService menuService;

    /**
     * Get the active carte menu with all items and their attributes
     * GET /api/carte-menu/active
     */
    @GET
    @Path("/active")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActiveCarteMenu() {
        List<CarteMenuItem> items = menuService.getActiveCarteMenuItems();

        if (items.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No active carte menu found")
                    .build();
        }

        return Response.ok(items).build();
    }
}
