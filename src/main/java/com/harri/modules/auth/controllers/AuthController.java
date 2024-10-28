package com.harri.modules.auth.controllers;

import com.harri.modules.auth.entities.dto.TokenDTO;
import com.harri.modules.auth.entities.dto.UserDTO;
import com.harri.modules.auth.services.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    UserService userService;

    @POST
    @RolesAllowed("Admin")
    @Path("/register")
    public Response save(UserDTO userDTO) {
        userService.save(userDTO.getUsername(), userDTO.getPassword());
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Path("/login")
    @PermitAll
    public Response login(UserDTO userDTO) {
        try {
            String token = userService.authenticate(userDTO.getUsername(), userDTO.getPassword());
            return Response.ok(new TokenDTO(token)).build();
        } catch (SecurityException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Incorrect user or password.").build();
        }
    }
}