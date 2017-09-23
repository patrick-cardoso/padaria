package com.unibave.padaria.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String descricao;

    @ManyToMany(mappedBy = "produtos",fetch = FetchType.EAGER)
    private List<Estabelecimento> estabelecimentos = new ArrayList<>();

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", descricao=" + descricao + ", estabelecimentos=" + estabelecimentos + '}';
    }
    
    
}
