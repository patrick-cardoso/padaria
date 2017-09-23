package com.unibave.padaria.resource;

import com.unibave.padaria.model.Estabelecimento;
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

@Api(value = "Cadastro de estabelecimentos", tags = "produtores",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Path("/estabelecimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EstabelecimentoResource {

    @ApiOperation(value = "Adiciona estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Produtor adicionado com sucesso", response = Produtor.class)
    })
    @POST
    Response adiciona(@Valid final Estabelecimento aluno);

    @ApiOperation(value = "Atualiza estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estabelecimento atualizado com sucesso", response = Produtor.class)
        ,
        @ApiResponse(code = 404, message = "Estabelecimento não encontrado")
    })
    @PUT
    @Path(value = "{id}")
    Response atualiza(@PathParam(value = "id") final Long id, @Valid final Estabelecimento estabelecimeto);

    @ApiOperation(value = "Exclui estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Estabelecimento excluído com sucesso")
        ,
        @ApiResponse(code = 404, message = "Estabelecimento não encontrado")
    })
    @DELETE
    @Path(value = "{id}")
    Response deleta(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Busca estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estabelec retornado com sucesso", response = Produtor.class)
        ,
        @ApiResponse(code = 404, message = "Produtor não encontrado")
    })
    @GET
    @Path(value = "{id}")
    Response busca(@PathParam(value = "id") final Long id);

    @GET
    Response lista(@QueryParam(value = "nome") final String nome,
            @QueryParam(value = "page") @DefaultValue("0") final int page,
            @QueryParam(value = "limit") @DefaultValue("10") final int limit,
            @QueryParam(value = "sort") @DefaultValue("id") final String sort,
            @QueryParam(value = "direction") @DefaultValue("asc") final String direction);

    @GET
    @Path(value = "{codigo}/produtos")
    Response buscaProdutos(@PathParam(value = "id") final Long id);

    @GET
    @Path(value = "{codigo}/produtos-disponiveis")
    Response buscaProdutosDisponiveis(@PathParam(value = "id") final Long id);

}
