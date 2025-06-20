package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.RacaDTO;
import com.example.pssapi.model.entity.Raca;

import com.example.pssapi.service.RacaService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/racas")
@RequiredArgsConstructor

public class RacaController {

    private final RacaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Raca> racas = service.getRacas();
        return ResponseEntity.ok(racas.stream().map(RacaDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Raca> raca = service.getRacaById(id);
        if (!raca.isPresent()) {
            return new ResponseEntity("Raça não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(raca.map(RacaDTO::create));
    }


    public Raca converter(RacaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Raca.class);
    }
}
    
