package com.unibave.padaria.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(
        @NamedQuery(name = "estabelecimento.lista", query = "SELECT a FROM Estabelecimento a"))
public class Estabelecimento {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;

    @Column
    private String endereco;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "estabelecimento_has_produtos", joinColumns
            = {
                @JoinColumn(name = "estabelecimento_id")}, inverseJoinColumns
            = {
                @JoinColumn(name = "produto_id")})
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "estabelecimento", fetch = FetchType.LAZY)
    private List<ProdutoDisponivel> produtosDisponivels = new ArrayList<>();

    public String getEndereco() {
        return endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public void addProduto(final Produto produto) {

        produto.getEstabelecimentos().add(this);
        System.out.println(produto.toString());
        produtos.add(produto);
    }

    public void removeProduto(final Produto produto) {
        produto.getEstabelecimentos().add(this);
        produtos.remove(produto);
    }

    public List<ProdutoDisponivel> getProdutosDisponiveis() {
        return Collections.unmodifiableList(produtosDisponivels);
    }

    public void addProdutoDisponiveis(final ProdutoDisponivel produtoDisponivel) {
        produtoDisponivel.setEstabelecimento(this);
        produtosDisponivels.add(produtoDisponivel);
    }

    public void removeDisponiveis(final ProdutoDisponivel produtoDisponivel) {
        produtosDisponivels.remove(produtoDisponivel);
    }

    @Override
    public String toString() {
        return "Estabelecimento{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + '}';
    }
}
