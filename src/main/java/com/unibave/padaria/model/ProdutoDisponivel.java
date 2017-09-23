package com.unibave.padaria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoDisponivel {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String data;

    @ManyToOne
    private Estabelecimento estabelecimento;

    @ManyToOne
    private Produto produto;

    public void setData(String data) {
        this.data = data;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getData() {
        return data;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return "ProdutoDisponivel{" + "id=" + id + ", data=" + data + ", estabelecimento=" + estabelecimento + ", produto=" + produto + '}';
    }

}
