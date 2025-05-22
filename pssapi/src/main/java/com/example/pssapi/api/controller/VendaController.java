package com.example.pssapi.api.controller;


import com.example.pssapi.api.dto.VendaDTO;
import com.example.pssapi.model.entity.Venda;
import com.example.pssapi.service.VendaService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vendas")
@RequiredArgsConstructor

public class VendaController {

    private final VendaService service;

    public Venda converter(VendaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Venda.class);
    }
}
