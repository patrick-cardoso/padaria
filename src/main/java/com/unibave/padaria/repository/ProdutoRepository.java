package com.unibave.padaria.repository;

import com.unibave.padaria.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

    Page<Produto> findByDescricaoContaining(Pageable pageable, String descricao);
}
