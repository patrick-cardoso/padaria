package com.unibave.padaria.resource;

import com.unibave.padaria.model.Estabelecimento;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estabelecimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EstabelecimentoResource {

    @POST
    Response adiciona(@Valid final Estabelecimento aluno);

    @PUT
    @Path(value = "{id}")
    Response atualiza(@PathParam(value = "id") final Long codigo, @Valid final Estabelecimento estabelecimeto);

    @DELETE
    @Path(value = "{id}")
    Response deleta(@PathParam(value = "id") final Long codigo);

    @GET
    @Path(value = "{id}")
    Response busca(@PathParam(value = "id") final Long codigo);

    @GET
    Response lista(@QueryParam(value = "nome") final String nome,
            @QueryParam(value = "page") @DefaultValue("0") final int page,
            @QueryParam(value = "limit") @DefaultValue("10") final int limit,
            @QueryParam(value = "sort") @DefaultValue("codigo") final String sort,
            @QueryParam(value = "direction") @DefaultValue("asc") final String direction);

}
