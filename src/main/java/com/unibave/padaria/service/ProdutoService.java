package com.unibave.padaria.service;

import com.unibave.padaria.model.Produto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Produto adiciona(Produto novoProduto);

    Produto atualiza(Long codigo, Produto produto);

    /**
     * Busca o aluno
     *
     * @param codigo Código do aluno
     * @return Aluno se existir
     */
    Optional<Produto> busca(Long codigo);

    void deleta(Long codigo);

    Page<Produto> lista(Pageable pageable, String nome);

}
