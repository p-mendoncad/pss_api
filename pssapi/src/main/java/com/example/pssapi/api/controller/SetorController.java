package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.setorDTO;
import com.example.pssapi.model.entity.setor;
import com.example.pssapi.service.setorService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/setores")
@RequiredArgsConstructor

public class setorController {

    private final SetorService service;

    public Setor converter(setorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, setor.class);
    }
}
