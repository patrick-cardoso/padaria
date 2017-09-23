
package com.unibave.padaria.resource;

import com.unibave.padaria.model.Produto;
import com.unibave.padaria.representation.ProdutoDTO;
import com.unibave.padaria.service.ProdutoServiceImpl;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProdutoResourceImpl implements ProdutoResource {

    @Inject
    private ProdutoServiceImpl service;

    @Override
    public Response adiciona(Produto produto) {
        return Response.ok(service.adiciona(produto)).build();
    }

    @Override
    public Response atualiza(Long id, Produto estabelecimeto) {
        return Response.ok(service.atualiza(id, estabelecimeto)).build();
    }

    @Override
    public Response deleta(Long id) {
        service.deleta(id);
        return Response.noContent().build();
    }

    @Override
    public Response busca(Long id) {
        Produto produto = service.busca(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(produto).build();
    }

    @Override
    public Response lista(String nome, int page, int limit, String sort, String direction) {
        Pageable pageagle = new PageRequest(page, limit,
                Sort.Direction.fromString(direction), sort);
        return Response.ok(service.lista(pageagle, nome)).build();
    }
}
