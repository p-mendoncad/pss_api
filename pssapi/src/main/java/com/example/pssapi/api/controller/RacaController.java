package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.RacaDTO;
import com.example.pssapi.model.entity.Raca;

import com.example.pssapi.service.RacaService;

import org.modelmapper.ModelMapper;

import com.example.pssapi.api.dto.RacaDTO;
import com.example.pssapi.service.RacaService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/racas")
@RequiredArgsConstructor

public class RacaController {

    private final RacaService service;

    public Raca converter(RacaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Raca.class);
    }
}
    
