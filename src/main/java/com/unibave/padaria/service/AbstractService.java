package com.unibave.padaria.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T> {

    T adiciona(T novoAluno);

    T atualiza(Long codigo, T aluno);

    /**
     * Busca o aluno
     * 
     * @param codigo Código do aluno
     * @return Aluno se existir
     */
    Optional<T> busca(Long codigo);

    void deleta(Long codigo);

    Page<T> lista(Pageable pageable, String nome);

}