package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.ItemVenda;
import com.example.pssapi.model.repository.ItemVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemVendaService {

    private final ItemVendaRepository repository;

    public ItemVendaService(ItemVendaRepository repository) {
        this.repository = repository;
    }

    public List<ItemVenda> getAll() {
        return repository.findAll();
    }

    public Optional<ItemVenda> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ItemVenda save(ItemVenda itemVenda) {
        validar(itemVenda);
        return repository.salvar(itemVenda);
    }

    @Transactional
    public void delete(ItemVenda itemVenda) {
        Objects.requireNonNull(itemVenda.getId(), "ID do ItemVenda não pode ser nulo");
        repository.delete(itemVenda);
    }

    public void validar(ItemVenda itemVenda) {
        if (itemVenda.getVenda() == null || itemVenda.getVenda().getId() == null || itemVenda.getVenda().getId() == 0) {
            throw new RegraNegocioException("ItemVenda inválido: Venda deve ser informada.");
        }
        if (itemVenda.getIdProduto() == null || itemVenda.getIdProduto() == 0) {
            throw new RegraNegocioException("ItemVenda inválido: Id do produto deve ser informado.");
        }
        if (itemVenda.getQuantidade() == null || itemVenda.getQuantidade() <= 0) {
            throw new RegraNegocioException("ItemVenda inválido: Quantidade deve ser maior que zero.");
        }
        if (itemVenda.getSubtotal() == null || itemVenda.getSubtotal().doubleValue() < 0) {
            throw new RegraNegocioException("ItemVenda inválido: Subtotal deve ser positivo.");
        }
    }
}
