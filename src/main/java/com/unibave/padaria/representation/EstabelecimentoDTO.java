package com.unibave.padaria.representation;

import com.unibave.padaria.model.Estabelecimento;
import com.unibave.padaria.model.Produto;
import com.unibave.padaria.model.ProdutoDisponivel;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EstabelecimentoDTO {

    private Long id;

    private String nome;

    private String endereco;

    private List<Produto> produtos = new ArrayList<>();

    private List<ProdutoDisponivel> produtosDisponiveis = new ArrayList<>();

    public EstabelecimentoDTO(Long id, String nome, String endereco, List<Produto> produtos, List<ProdutoDisponivel> produtosDisponiveis) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }
    
    public EstabelecimentoDTO(final Estabelecimento estabelecimento) {
        this.id = estabelecimento.getId();
        this.nome = estabelecimento.getNome();
        this.endereco = estabelecimento.getEndereco();
        this.produtos = estabelecimento.getProdutos();
        this.produtosDisponiveis = estabelecimento.getProdutosDisponiveis();
    }
}
