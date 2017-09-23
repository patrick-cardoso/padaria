package com.unibave.padaria.service;

import com.unibave.padaria.model.Produto;
import com.unibave.padaria.repository.ProdutoRepository;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    private ProdutoRepository repository;

    @Override
    @Transactional
    public Produto adiciona(Produto novoProduto) {
        return repository.save(novoProduto);
    }

    @Override
    @Transactional
    public Produto atualiza(Long codigo, Produto produto) {
        if (!codigo.equals(produto.getId())) {
            throw new IllegalArgumentException("Código inválido");
        }
        return repository.save(produto);
    }

    @Override
    public Optional<Produto> busca(Long codigo) {
        return Optional.ofNullable(repository.findOne(codigo));
    }

    @Override
    @Transactional
    public void deleta(final Long codigo) {
        busca(codigo).ifPresent(repository::delete);
    }

    @Override
    public Page<Produto> lista(Pageable pageable, String descricao) {
        return descricao != null
                ? repository.findByDescricaoContaining(pageable, descricao)
                : repository.findAll(pageable);
    }

}
