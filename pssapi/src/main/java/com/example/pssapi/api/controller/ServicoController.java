package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.ServicoDTO;
import com.example.pssapi.api.dto.servicoDTO;
import com.example.pssapi.model.entity.Servico;
import com.example.pssapi.model.entity.cargo;
import com.example.pssapi.model.entity.servico;
import com.example.pssapi.service.CargoService;
import com.example.pssapi.service.servicoService;
import com.example.pssapi.service.cargoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
@CrossOrigin

public class ServicoController {

    private final Servicoservice service;


    public Servico converter(ServicoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        servico servico = modelMapper.map(dto, Servico.class);
        if (dto.getIdcargo() != null) {
            Optional<cargo> cargo = CargoService.getcargoById(dto.getIdcargo());
            if (!cargo.isPresent()) {
                servico.setCargo(null);
            } else {
                servico.setcargo(cargo.get());
            }
        }
        return servico;
    }
}
