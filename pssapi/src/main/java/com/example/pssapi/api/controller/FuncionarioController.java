package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.FuncionarioDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Cargo;
import com.example.pssapi.model.entity.Funcionario;
import com.example.pssapi.service.FuncionarioService;
import com.example.pssapi.service.CargoService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
@CrossOrigin
@Api("API de Funcionario")


public class FuncionarioController {

    private final FuncionarioService service;
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Funcionario> funcionarios = service.getFuncionarios();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Funcionario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Funcionario encontrado"),
            @ApiResponse(code = 404, message = "Funcionario não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping()
    @ApiOperation("Salva um novo Funcionario")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Funcionario salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar o Funcionario")
    })
    public ResponseEntity post(@RequestBody FuncionarioDTO dto) {
        try {
            Funcionario funcionario = converter(dto);
            funcionario = service.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id,@RequestBody FuncionarioDTO dto) {
        if (!service.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = converter(dto);
            funcionario.setId(id);
            service.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return new ResponseEntity("Funcionário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(funcionario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Funcionario converter(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        if (dto.getIdCargo() != null) {
            Optional<Cargo> cargo = cargoService.getCargoById(dto.getIdCargo());
            if (!cargo.isPresent()) {
                funcionario.setCargo(null);
            } else {
                funcionario.setCargo(cargo.get());
            }
        }
        return funcionario;
    }
}
