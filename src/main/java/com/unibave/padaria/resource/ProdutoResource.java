package com.unibave.padaria.resource;

import com.unibave.padaria.model.Produto;
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

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProdutoResource {

    @POST
    Response adiciona(@Valid final Produto produto);

    @PUT
    @Path(value = "{id}")
    Response atualiza(@PathParam(value = "id") final Long id, @Valid final Produto produto);

    @DELETE
    @Path(value = "{id}")
    Response deleta(@PathParam(value = "id") final Long id);

    @GET
    @Path(value = "{id}")
    Response busca(@PathParam(value = "id") final Long id);

    @GET
    Response lista(@QueryParam(value = "nome") final String nome,
            @QueryParam(value = "page") @DefaultValue("0") final int page,
            @QueryParam(value = "limit") @DefaultValue("10") final int limit,
            @QueryParam(value = "sort") @DefaultValue("id") final String sort,
            @QueryParam(value = "direction") @DefaultValue("asc") final String direction);

}
