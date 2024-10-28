package com.harri.modules.banking.controllers;

import com.harri.modules.banking.entities.jpa.Customer;
import com.harri.modules.banking.services.CustomerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/customer")
public class CustomerRestController {

    @Inject
    CustomerService customerService;

    @POST
    @RolesAllowed({"Admin", "User"})
    public Response save(Customer customer) {
        customerService.save(customer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @RolesAllowed({"Admin", "User"})
    public Response update(Customer customer) {
        customerService.update(customer);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    @RolesAllowed({"Admin", "User"})
    @Path("/{id}")
    public Response find(@PathParam("id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) return Response.ok(customer).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @RolesAllowed({"Admin", "User"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (customerService.deleteById(id)) {

            return Response.ok().build();
        };
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
