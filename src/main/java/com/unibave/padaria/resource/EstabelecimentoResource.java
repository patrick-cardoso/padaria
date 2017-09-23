package com.unibave.padaria.resource;

import com.unibave.padaria.model.Estabelecimento;
import com.unibave.padaria.model.Produto;
import com.unibave.padaria.model.ProdutoDisponivel;
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

@Api(value = "Cadastro de estabelecimentos", tags = "estabelecimentos",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Path("/estabelecimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface EstabelecimentoResource {

    @ApiOperation(value = "Adiciona estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estabelecimento adicionado com sucesso", response = Estabelecimento.class)
    })
    @POST
    Response adiciona(@Valid final Estabelecimento aluno);

    @ApiOperation(value = "Atualiza estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Estabelecimento atualizado com sucesso", response = Estabelecimento.class)
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
        @ApiResponse(code = 200, message = "Estabelecimento retornado com sucesso", response = Estabelecimento.class)
        ,
        @ApiResponse(code = 404, message = "Estabelecimento não encontrado")
    })
    @GET
    @Path(value = "{id}")
    Response busca(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Lista estabelecimentos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de estabelecimentos", response = Estabelecimento.class, responseContainer = "List")
    })
    @GET
    Response lista(@QueryParam(value = "nome") final String nome,
            @QueryParam(value = "page") @DefaultValue("0") final int page,
            @QueryParam(value = "limit") @DefaultValue("10") final int limit,
            @QueryParam(value = "sort") @DefaultValue("id") final String sort,
            @QueryParam(value = "direction") @DefaultValue("asc") final String direction);

    @ApiOperation(value = "Lista de produtos do estabelecimento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de produtos do estabelecimento", response = ProdutoDisponivel.class, responseContainer = "List")
    })
    @GET
    @Path(value = "{id}/produtos")
    Response buscaProdutos(@PathParam(value = "id") final Long id);

    @ApiOperation(value = "Lista de produtos dipsoníveis para venda")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de produtos disponíveis no estabelecimento", response = Produto.class, responseContainer = "List")
    })
    @GET
    @Path(value = "{id}/produtos-disponiveis")
    Response buscaProdutosDisponiveis(@PathParam(value = "id") final Long id);

}
