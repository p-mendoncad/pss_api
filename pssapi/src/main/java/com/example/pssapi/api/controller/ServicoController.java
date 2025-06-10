package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.ServicoDTO;
import com.example.pssapi.model.entity.Servico;
import com.example.pssapi.model.entity.Cargo;
import com.example.pssapi.service.CargoService;
import com.example.pssapi.service.ServicoService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor

public class ServicoController {

    private final ServicoService service;
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Servico> servicos = service.getServicos();
        return ResponseEntity.ok(servicos.stream().map(ServicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Servico> servico = service.getServicoById(id);
        if (!servico.isPresent()) {
            return new ResponseEntity("Servico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(servico.map(ServicoDTO::create));
    }


    public Servico converter(ServicoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Servico servico = modelMapper.map(dto, Servico.class);
        if (dto.getIdCargo() != null) {
            Optional<Cargo> cargos = cargoService.getCargoById(dto.getIdCargo());
            if (!cargos.isPresent()) {
                servico.setCargo(null);
            } else {
                servico.setCargo(cargos.get());
            }
        }
        return servico;
    }
}
