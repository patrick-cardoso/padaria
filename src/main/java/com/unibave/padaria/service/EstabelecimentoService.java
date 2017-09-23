package com.unibave.padaria.service;

import com.unibave.padaria.model.Estabelecimento;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstabelecimentoService {

    Estabelecimento adiciona(Estabelecimento novoAluno);

    Estabelecimento atualiza(Long codigo, Estabelecimento aluno);

    /**
     * Busca o aluno
     *
     * @param codigo Código do aluno
     * @return Aluno se existir
     */
    Optional<Estabelecimento> busca(Long codigo);

    void deleta(Long codigo);

    Page<Estabelecimento> lista(Pageable pageable, String nome);

}
