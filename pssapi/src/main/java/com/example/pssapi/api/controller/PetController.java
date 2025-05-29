package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.PetDTO;
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

    public Pet converter(PetDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Pet.class);
    }
}
