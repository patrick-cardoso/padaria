package com.unibave.padaria.resource;

import com.unibave.padaria.model.Estabelecimento;
import com.unibave.padaria.representation.EstabelecimentoDTO;
import com.unibave.padaria.service.EstabelecimentoServiceImpl;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoResourceImpl implements EstabelecimentoResource {

    @Inject
    private EstabelecimentoServiceImpl service;

    @Override
    public Response adiciona(Estabelecimento estabelecimento) {
        return Response.ok(service.adiciona(estabelecimento)).build();
    }

    @Override
    public Response atualiza(Long id, Estabelecimento estabelecimeto) {
        return Response.ok(service.atualiza(id, estabelecimeto)).build();
    }

    @Override
    public Response deleta(Long id) {
        service.deleta(id);
        return Response.noContent().build();
    }

    @Override
    public Response busca(Long id) {
        Estabelecimento estabelecimento = service.busca(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new EstabelecimentoDTO(estabelecimento)).build();
    }

    @Override
    public Response lista(String nome, int page, int limit, String sort, String direction) {
        Pageable pageagle = new PageRequest(page, limit,
                Sort.Direction.fromString(direction), sort);
        return Response.ok(service.lista(pageagle, nome)).build();
    }

    @Override
    public Response buscaProdutos(Long id) {
        Estabelecimento estabelecimento = service.busca(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new EstabelecimentoDTO(estabelecimento).getProdutos()).build();
    }

    @Override
    public Response buscaProdutosDisponiveis(Long id) {
        Estabelecimento estabelecimento = service.busca(id)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new EstabelecimentoDTO(estabelecimento).getProdutosDisponiveis()).build();
    }
}
