/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unibave.padaria.representation;

import com.unibave.padaria.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDTO {

    private Long id;

    private String descricao;

    private List<EstabelecimentoDTO> estabelecimentos = new ArrayList<>();

    public ProdutoDTO(Long id, String descricao, List<EstabelecimentoDTO> estabelecimentos) {
        this.id = id;
        this.descricao = descricao;
        this.estabelecimentos = estabelecimentos;
    }

    public ProdutoDTO(final Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.estabelecimentos = produto.getEstabelecimentos().isEmpty() ? null : produto.getEstabelecimentos().stream().map(m -> new EstabelecimentoDTO(m)).collect(Collectors.toList());
    }
}
