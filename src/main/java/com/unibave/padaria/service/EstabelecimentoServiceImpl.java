package com.unibave.padaria.service;

import com.unibave.padaria.model.Estabelecimento;
import com.unibave.padaria.repository.EstabelecimentoRepository;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    @Inject
    private EstabelecimentoRepository repository;

    @Override
    @Transactional
    public Estabelecimento adiciona(Estabelecimento novoEstabelecimento) {
        return repository.save(novoEstabelecimento);
    }

    @Override
    @Transactional
    public Estabelecimento atualiza(Long codigo, Estabelecimento estabelecimento) {
        if (!codigo.equals(estabelecimento.getId())) {
            throw new IllegalArgumentException("Código inválido");
        }
        return repository.save(estabelecimento);
    }

    @Override
    @Transactional
    public Optional<Estabelecimento> busca(Long codigo) {
        Optional<Estabelecimento> estabelecimento = Optional.ofNullable(repository.findOne(codigo));
        estabelecimento.get().getProdutosDisponiveis().size();
        return estabelecimento;
    }

    @Override
    @Transactional
    public void deleta(final Long codigo) {
        busca(codigo).ifPresent(repository::delete);
    }

    @Override
    @Transactional
    public Page<Estabelecimento> lista(Pageable pageable, String nome) {
        Page<Estabelecimento> lista = nome != null
                ? repository.findByNomeContaining(pageable, nome)
                : repository.findAll(pageable);
        if (lista.hasContent()) {
            lista.iterator().next().getProdutosDisponiveis().size();
        }
        return lista;
    }

}
