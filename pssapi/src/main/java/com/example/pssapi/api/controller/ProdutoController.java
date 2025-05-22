package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.ProdutoDTO;
import com.example.pssapi.model.entity.Fornecedor;
import com.example.pssapi.model.entity.Produto;
import com.example.pssapi.service.FornecedorService;
import com.example.pssapi.service.ProdutoService;

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


    public Produto converter(ProdutoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Produto Produto = modelMapper.map(dto, Produto.class);
        if (dto.getIdFornecedor() != null) {
            Optional<Fornecedor> Fornecedor = FornecedorService.getFornecedorById(dto.getIdFornecedor());
            if (!Fornecedor.isPresent()) {
                Produto.setFornecedor(null);
            } else {
                Produto.setFornecedor(Fornecedor.get());
            }
        }
        return Produto;
    }
}
