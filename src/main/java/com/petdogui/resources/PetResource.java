package com.petdogui.resources;

import com.petdogui.model.ErroValidacao;
import com.petdogui.model.Pet;
import com.petdogui.service.PetService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pet")
public class PetResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Pet pet) {
        try {
            PetService service = new PetService();
            return Response.ok(service.insert(pet)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Pet pet) {
        try {
            PetService service = new PetService();
            return Response.ok(service.update(pet)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            PetService service = new PetService();
            service.delete(id);
            return Response.ok().build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response findById(@PathParam("id") int id) {
        try {
            PetService service = new PetService();
            return Response.ok(service.findById(id)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            PetService service = new PetService();
            return Response.ok(service.findAll()).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }
}
