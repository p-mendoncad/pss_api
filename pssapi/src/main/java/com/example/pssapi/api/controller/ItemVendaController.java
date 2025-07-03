package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.ItemVendaDTO;
import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.ItemVenda;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.model.entity.Produto;
import com.example.pssapi.model.entity.Venda;
import com.example.pssapi.service.ItemVendaService;
import com.example.pssapi.service.ProdutoService;
import com.example.pssapi.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/itemVendas")
@RequiredArgsConstructor

public class ItemVendaController {

    private final ItemVendaService service;
    private final VendaService vendaService;
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity get() {
        List<ItemVenda> itens = service.getAll();
        return ResponseEntity.ok(itens.stream().map(ItemVendaDTO::create).collect(Collectors.toList()));

    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<ItemVenda> item = service.getItemVendaById(id);
        return ResponseEntity.ok(item.map(ItemVendaDTO::create));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ItemVendaDTO dto) {
        try {
            ItemVenda item = converter(dto);
            item = service.salvar(item);
            return new ResponseEntity(item, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ItemVendaDTO dto) {
        if (!service.getItemVendaById(id).isPresent()) {
            return new ResponseEntity("ItemVenda não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            ItemVenda item = converter(dto);
            item = service.salvar(item);
            return ResponseEntity.ok(item);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<ItemVenda> item = service.getItemVendaById(id);
        if (!item.isPresent()) {
            return new ResponseEntity<>("ItemVenda não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(item.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ItemVenda converter(ItemVendaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        ItemVenda item = modelMapper.map(dto, ItemVenda.class);
        if (dto.getIdVenda() != null) {
            Optional<Venda> venda = vendaService.getVendaById(dto.getIdVenda());
            if (!venda.isPresent()) {
                item.setVenda(null);
            } else {
                item.setVenda(venda.get());
            }
        }

        if (dto.getIdProduto() != null) {
            Optional<Produto> produto = produtoService.getProdutoById(dto.getIdProduto());
            if (!produto.isPresent()) {
            }
        }

        return item;
    }
}
