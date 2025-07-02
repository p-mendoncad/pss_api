package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Produto;
import org.springframework.stereotype.Service;

import com.example.pssapi.model.repository.ProdutoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        if (Produto.getNome() == null || Produto.getNome().trim().equals("")) {
            throw new RegraNegocioException("Produto inválido: Informe o nome do produto.");
        }
        if (Produto.getVencimento() == null || Produto.getVencimento().trim().equals("")) {
            throw new RegraNegocioException("Produto inválido: Informe a data de vencimento.");
        }
        if (Produto.getQuantidade() == null || Produto.getVencimento().trim().equals("")) {
            throw new RegraNegocioException("Produto inválido: Informe a quantidade.");
        }
        if (Produto.getFornecedor().getId() == null || Produto.getFornecedor().getId() == 0) {
            throw new RegraNegocioException("Produto inválido: Informe a quantidade.");
        }
        if (Produto.getLote() == null || Produto.getLote().trim().equals("")) {
            throw new RegraNegocioException("Produto inválido: Informe o lote.");
        }
        if (Produto.getPrecoCompra() == null || Produto.getPrecoCompra().equals("") ) {
            throw new RegraNegocioException("Produto inválido: Informe o preço de compra.");
        }
        if (Produto.getPrecoCompra().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraNegocioException("Produto inválido: Preço de compra não pode ser negativo.");
        }
        if (Produto.getPrecoVenda() == null || Produto.getPrecoVenda().equals("") ) {
            throw new RegraNegocioException("Produto inválido: Informe o preço de compra.");
        }
        if (Produto.getPrecoVenda().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraNegocioException("Produto inválido: Preço de venda não pode ser negativo.");
        }



    }
}
