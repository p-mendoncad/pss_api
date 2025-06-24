package com.example.pssapi.api.controller;


import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Pet;
import lombok.RequiredArgsConstructor;
import com.example.pssapi.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor

public class PetController {

    private final PetService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Pet> pets = service.getPets();
        return ResponseEntity.ok(pets.stream().map(PetDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Pet> pet = service.getPetById(id);
        if (!pet.isPresent()) {
            return new ResponseEntity("Pet não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pet.map(PetDTO::create));
    }

    @PostMapping()
    public ResponseEntity post( PetDTO dto) {
        try {
            Pet pet = converter(dto);
            pet = service.salvar(pet);
            return new ResponseEntity(pet, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, PetDTO dto) {
        if (!service.getPetById(id).isPresent()) {
            return new ResponseEntity("Pet não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Pet pet = converter(dto);
            pet.setId(id);
            service.salvar(pet);
            return ResponseEntity.ok(pet);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Pet converter(PetDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Pet.class);
    }
}
