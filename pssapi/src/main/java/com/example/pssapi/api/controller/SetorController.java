package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.CargoDTO;
import com.example.pssapi.api.dto.SetorDTO;
import com.example.pssapi.api.dto.SetorDTO;
import com.example.pssapi.model.entity.Cargo;
import com.example.pssapi.model.entity.Setor;
import com.example.pssapi.model.entity.Setor;
import com.example.pssapi.service.SetorService;
import com.example.pssapi.service.SetorService;
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
@RequestMapping("/api/v1/setores")
@RequiredArgsConstructor
@CrossOrigin

public class SetorController {

    private final SetorService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Setor> setores = service.getSetores();
        return ResponseEntity.ok(setores.stream().map(SetorDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Setor> setor = service.getSetorById(id);
        if (!setor.isPresent()) {
            return new ResponseEntity("Setor não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(setor.map(SetorDTO::create));
    }


    public Setor converter(SetorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Setor.class);
    }
}
