package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.ProdutoDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Fornecedor;
import com.example.pssapi.model.entity.Produto;
import com.example.pssapi.model.entity.Setor;
import com.example.pssapi.service.FornecedorService;
import com.example.pssapi.service.ProdutoService;
import com.example.pssapi.service.SetorService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor

public class ProdutoController {

    private final ProdutoService service;
    private final FornecedorService fornecedorService;
    private final SetorService setorService;

    @GetMapping()
    public ResponseEntity get() {
        List<Produto> produtos = service.getProdutos();
        return ResponseEntity.ok(produtos.stream().map(ProdutoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        if (!produto.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(produto.map(ProdutoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ProdutoDTO dto) {
        try {
            Produto produto = converter(dto);
            produto = service.salvar(produto);
            return new ResponseEntity(produto, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id,@RequestBody ProdutoDTO dto) {
        if (!service.getProdutoById(id).isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Produto produto = converter(dto);
            produto.setId(id);
            service.salvar(produto);
            return ResponseEntity.ok(produto);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        if (!produto.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(produto.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Produto converter(ProdutoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Produto Produto = modelMapper.map(dto, Produto.class);
        if (dto.getIdFornecedor() != null) {
            Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(dto.getIdFornecedor());
            if (!fornecedor.isPresent()) {
                Produto.setFornecedor(null);
            } else {
                Produto.setFornecedor(fornecedor.get());
            }
        }
        if (dto.getIdSetor() != null) {
            Optional<Setor> setor = setorService.getSetorById(dto.getIdSetor());
            if (!setor.isPresent()) {
                Produto.setSetor(null);
            } else {
                Produto.setSetor(setor.get());
            }
        }
        return Produto;
    }
}
