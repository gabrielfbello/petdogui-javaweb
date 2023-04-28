package com.petdogui.resources;

import com.petdogui.model.Atendimento;
import com.petdogui.model.ErroValidacao;
import com.petdogui.service.AtendimentoService;
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

@Path("atendimento")
public class AtendimentoResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(Atendimento atendimento) {
        try {
            AtendimentoService service = new AtendimentoService();
            return Response.ok(service.insert(atendimento)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Atendimento atendimento) {
        try {
            AtendimentoService service = new AtendimentoService();
            return Response.ok(service.update(atendimento)).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            AtendimentoService service = new AtendimentoService();
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
            AtendimentoService service = new AtendimentoService();
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
            AtendimentoService service = new AtendimentoService();
            return Response.ok(service.findAll()).build();
        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }
    }
}