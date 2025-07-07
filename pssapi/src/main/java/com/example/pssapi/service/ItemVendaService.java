package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.ItemVenda;
import com.example.pssapi.model.repository.ItemVendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public Optional<ItemVenda> getItemVendaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ItemVenda salvar(ItemVenda itemVenda) {
        BigDecimal quantidade = new BigDecimal(itemVenda.getQuantidade());
        BigDecimal preco = itemVenda.getProduto().getPrecoVenda();
        itemVenda.setSubtotal(quantidade.multiply(preco));
        validar(itemVenda);
        return repository.save(itemVenda);
    }

    @Transactional
    public void excluir(ItemVenda itemVenda) {
        Objects.requireNonNull(itemVenda.getId(), "ID do ItemVenda não pode ser nulo");
        repository.delete(itemVenda);
    }

    public void validar(ItemVenda itemVenda) {
        if (itemVenda.getProduto() == null || itemVenda.getProduto().getId() == 0) {
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
