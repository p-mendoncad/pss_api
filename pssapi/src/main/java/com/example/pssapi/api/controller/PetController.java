package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.service.ClienteService;
import com.example.pssapi.service.PetService;
import com.example.pssapi.service.RacaService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
@CrossOrigin
@Api("API de Pet")
public class PetController {
    private final PetService service;
    private final RacaService racaService;
    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAll() {
        List<Pet> pets = service.getPets();
        List<PetDTO> dtos = pets.stream()
                .map(PetDTO::create)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Pet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pet encontrado"),
            @ApiResponse(code = 404, message = "Pet não encontrado")
    })
    public ResponseEntity<PetDTO> getById(@PathVariable Long id) {
        Optional<Pet> pet = service.getPetById(id);
        return pet.map(value -> ResponseEntity.ok(PetDTO.create(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation("Salvar um novo Pet")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pet salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar o Pet")
    })
    public ResponseEntity<?> create(@RequestBody PetDTO dto) {
        try {
            Pet pet = converter(dto);
            pet = service.salvar(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(pet);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PetDTO dto) {
        if (!service.getPetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Pet> pet = service.getPetById(id);
        if (!pet.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        try {
            service.excluir(pet.get());
            return ResponseEntity.noContent().build();
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Pet converter(PetDTO dto) {
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setNome(dto.getNome());
        pet.setPeso(dto.getPeso());
        pet.setDataNasc(dto.getDataNasc());
        pet.setHistoVac(dto.getHistoVac());
        pet.setSexo(dto.isSexo());
        pet.setHistServ(dto.getHistServ());
        pet.setObs(dto.getObs());

        if (dto.getIdRaca() != null) {
            racaService.getRacaById(dto.getIdRaca()).ifPresent(pet::setRaca);
        }

        if (dto.getIdCliente() != null) {
            clienteService.getClienteById(dto.getIdCliente()).ifPresent(pet::setCliente);
        }

        return pet;
    }
}