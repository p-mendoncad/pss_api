package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.CargoDTO;
import com.example.pssapi.api.dto.SetorDTO;
import com.example.pssapi.api.dto.SetorDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Cargo;
import com.example.pssapi.model.entity.Setor;
import com.example.pssapi.model.entity.Setor;
import com.example.pssapi.service.SetorService;
import com.example.pssapi.service.SetorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity post(SetorDTO dto) {
        try {
            Setor setor = converter(dto);
            setor = service.salvar(setor);
            return new ResponseEntity(setor, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, SetorDTO dto) {
        if (!service.getSetorById(id).isPresent()) {
            return new ResponseEntity("Setor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Setor setor = converter(dto);
            setor.setId(id);
            service.salvar(setor);
            return ResponseEntity.ok(setor);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public Setor converter(SetorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Setor.class);
    }
}
