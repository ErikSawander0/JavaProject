package com.miun.martinclass.demo.scheduling.api;

import com.miun.martinclass.demo.scheduling.entity.Employee;
import com.miun.martinclass.demo.scheduling.service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthApi {

    @Inject
    EmployeeService employeeService;

    /**
     * POST /api/login
     * Request body: { "username": "string", "password": "string" }
     * Response: { "employeeId": int, "username": "string", "name": "string", "token": null }
     */
    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        if (request == null || request.username == null || request.password == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Username and password are required"))
                    .build();
        }

        Employee employee = employeeService.authenticate(request.username, request.password);

        if (employee == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Invalid username or password"))
                    .build();
        }

        LoginResponse response = new LoginResponse();
        response.employeeId = employee.getId();
        response.username = employee.getUsername();
        response.name = employee.getName();
        response.token = null; // Token auth not implemented, but field is ready

        return Response.ok(response).build();
    }

    // ========== Request/Response DTOs ==========

    public static class LoginRequest {
        public String username;
        public String password;
    }

    public static class LoginResponse {
        public Long employeeId;
        public String username;
        public String name;
        public String token;
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}