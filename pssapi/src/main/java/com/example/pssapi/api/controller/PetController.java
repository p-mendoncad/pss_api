package com.example.pssapi.api.controller;


import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.model.entity.Raca;
import lombok.RequiredArgsConstructor;
import com.example.pssapi.service.PetService;
import com.example.pssapi.service.RacaService;
import com.example.pssapi.service.ClienteService;
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
    private final RacaService racaService;
    private final ClienteService clienteService;

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
    public ResponseEntity post(@RequestBody PetDTO dto) {
        try {
            Pet pet = converter(dto);
            pet = service.salvar(pet);
            return new ResponseEntity(pet, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id,@RequestBody PetDTO dto) {
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

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Pet> pet = service.getPetById(id);
        if (!pet.isPresent()) {
            return new ResponseEntity("Pet não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(pet.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Pet converter(PetDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Pet pet = modelMapper.map(dto, Pet.class);

        if (dto.getIdRaca() != null) {
            Optional<Raca> raca = racaService.getRacaById(dto.getIdRaca());
            pet.setRaca(raca.orElse(null));
        }

        if (dto.getIdCliente() != null) {
            Optional<Cliente> cliente = clienteService.getClienteById(dto.getIdCliente());
            pet.setCliente(cliente.orElse(null));
        }

        return pet;
    }

}
