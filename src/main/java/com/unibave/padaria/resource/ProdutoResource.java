package com.unibave.padaria.resource;

import com.unibave.padaria.model.Produto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "Cadastro de produtos", tags = "produtos",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProdutoResource {

    @ApiOperation(value = "Adiciona produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produto adicionado com sucesso", response = Produto.class)
    })
    @POST
    Response adiciona(@Valid final Produto produto);

    @ApiOperation(value = "Atualiza produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produto atualizado com sucesso", response = Produto.class)
        ,
        @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    @PUT
    @Path(value = "{id}")
    Response atualiza(@PathParam(value = "id") final Long id, @Valid final Produto produto);

    @ApiOperation(value = "Exclui produto")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Produto excluído com sucesso")
        ,
        @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    @DELETE
    @Path(value = "{id}")
    Response deleta(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Busca produto")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produto retornado com sucesso", response = Produto.class)
        ,
        @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    @GET
    @Path(value = "{id}")
    Response busca(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Lista produtos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de produtos", response = Produto.class, responseContainer = "List")
    })
    @GET
    Response lista(@QueryParam(value = "nome") final String nome,
            @QueryParam(value = "page") @DefaultValue("0") final int page,
            @QueryParam(value = "limit") @DefaultValue("10") final int limit,
            @QueryParam(value = "sort") @DefaultValue("id") final String sort,
            @QueryParam(value = "direction") @DefaultValue("asc") final String direction);

}
