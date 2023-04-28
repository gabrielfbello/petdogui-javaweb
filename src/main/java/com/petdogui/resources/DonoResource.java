package com.petdogui.resources;

import com.petdogui.model.Dono;
import com.petdogui.service.DonoService;
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

import java.util.List;

@Path("dono")
public class DonoResource {

    private DonoService donoService = new DonoService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Dono dono) {
        Dono createdDono = null;
        try {
            createdDono = donoService.createDono(dono);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.CREATED).entity(createdDono).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Dono dono) {
        dono.setId(id);
        Dono updatedDono = null;
        try {
            updatedDono = donoService.updateDono(dono);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.OK).entity(updatedDono).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        try {
            donoService.deleteDono(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        Dono dono = null;
        try {
            dono = donoService.findDonoById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.OK).entity(dono).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Dono> donos = null;
        try {
            donos = donoService.findAllDonos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.OK).entity(donos).build();
    }
}
