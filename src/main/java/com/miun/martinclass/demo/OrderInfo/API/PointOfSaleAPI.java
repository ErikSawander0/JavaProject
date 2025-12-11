package com.miun.martinclass.demo.OrderInfo.API;

import com.miun.martinclass.demo.OrderInfo.entity.Party;
import com.miun.martinclass.demo.OrderInfo.entity.PartyClass;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pos") // API base path: /api/pos
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PointOfSaleAPI {

    @Inject
    PartyClass partyClass;

    @GET
    public Response makeGroup(@QueryParam("tableNumber") int tableNumber) {
        Party party = new Party();
        party.id = 67L;
        party.tableNum = 5;
        partyClass.createParty(party);
        return Response.ok(
                party
        ).build();
    }
}