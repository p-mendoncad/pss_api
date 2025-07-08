package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.RacaDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Raca;

import com.example.pssapi.service.RacaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("API de Raça")


public class RacaController {

    private final RacaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Raca> racas = service.getRacas();
        return ResponseEntity.ok(racas.stream().map(RacaDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de uma Raça")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Raça encontrada"),
            @ApiResponse(code = 404, message = "Raça não encontrada")
    })
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Raca> raca = service.getRacaById(id);
        if (!raca.isPresent()) {
            return new ResponseEntity("Raça não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(raca.map(RacaDTO::create));
    }
    @PostMapping()
    @ApiOperation("Salva uma nova Raça ")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Raça salva com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar uma Raça")
    })
    public ResponseEntity post(@RequestBody RacaDTO dto) {
        try {
            Raca raca = converter(dto);
            raca = service.salvar(raca);
            return new ResponseEntity(raca, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody RacaDTO dto) {
        if (!service.getRacaById(id).isPresent()) {
            return new ResponseEntity("Raça não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Raca raca = converter(dto);
            raca.setId(id);
            service.salvar(raca);
            return ResponseEntity.ok(raca);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Raca> raca = service.getRacaById(id);
        if (!raca.isPresent()) {
            return new ResponseEntity("Raça não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(raca.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Raca converter(RacaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Raca.class);
    }
}