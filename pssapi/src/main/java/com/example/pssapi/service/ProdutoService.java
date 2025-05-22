package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Produto;
import com.example.pssapi.model.repository.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> getProdutos() {
        return repository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Produto salvar(Produto Produto) {
        validar(Produto);
        return repository.save(Produto);
    }

    @Transactional
    public void excluir(Produto Produto) {
        Objects.requireNonNull(Produto.getId());
        repository.delete(Produto);
    }

    public void validar(Produto Produto) {
        if (Produto.getId() == null || Produto.getId().trim().equals("")) {
            throw new RegraNegocioException("Produto inválido");
        }
        if (Produto.getNome() == null || Produto.getNome().trim().equals("")) {
            throw new RegraNegocioException("Produto inválido");
        }
    }
}
