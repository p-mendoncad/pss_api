package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.servicoDTO;
import com.example.pssapi.model.entity.cargo;
import com.example.pssapi.model.entity.servico;
import com.example.pssapi.service.servicoService;
import com.example.pssapi.service.cargoService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor

public class ServicoController {

    private final Servicoservice service;


    public Servico converter(servicoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        servico servico = modelMapper.map(dto, servico.class);
        if (dto.getIdcargo() != null) {
            Optional<cargo> cargo = cargoService.getcargoById(dto.getIdcargo());
            if (!cargo.isPresent()) {
                servico.setcargo(null);
            } else {
                servico.setcargo(cargo.get());
            }
        }
        return servico;
    }
}
