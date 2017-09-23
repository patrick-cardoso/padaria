package com.unibave.padaria.repository;

import com.unibave.padaria.model.Estabelecimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends PagingAndSortingRepository<Estabelecimento, Long> {

    Page<Estabelecimento> findByNomeContaining(Pageable pageable, String nome);
}
