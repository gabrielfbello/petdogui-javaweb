package com.petdogui.resources;

import com.petdogui.model.ErroValidacao;
import com.petdogui.service.ServicoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import com.petdogui.model.Servico;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("servico")
public class ServicosResource {
    @POST
    @Consumes(APPLICATION_JSON)
    public Response insert(Servico servico){

        try {

            ServicoService service = new ServicoService();

            return Response.ok(service.insert(servico)).build();

        } catch (Exception ex) {

            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();

        }

    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response update(Servico servico) {

        try {

            ServicoService service = new ServicoService();

            return Response.ok(service.update(servico)).build();

        } catch (Exception ex) {

            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();

        }

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        //localhost:8080/pet-dogui/servico/5
        try {

            ServicoService service = new ServicoService();
            service.delete(id);

            return Response.ok().build();

        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }

    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") int id) {

        try {

            ServicoService service = new ServicoService();
            return Response.ok(service.findById(id)).build();

        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }

    }

    @GET
    public Response findAll() {
        try {

            ServicoService service = new ServicoService();
            return Response.ok(service.findByAll()).build();

        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }

    }

    @GET
    @Path("filter")
    public Response findAllWithParameters(@QueryParam("descricao") String descricao) {

        try {

            ServicoService service = new ServicoService();
            return Response.ok(service.findWithParameteres(descricao)).build();

        } catch (Exception ex) {
            return Response.serverError().entity(
                    new ErroValidacao(ex.getMessage())).build();
        }

    }


}