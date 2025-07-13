package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.ServicoDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Servico;
import com.example.pssapi.model.entity.Cargo;
import com.example.pssapi.service.CargoService;
import com.example.pssapi.service.ServicoService;

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
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
@CrossOrigin
@Api("API de Produto")


public class ServicoController {

    private final ServicoService service;
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Servico> servicos = service.getServicos();
        return ResponseEntity.ok(servicos.stream().map(ServicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Serviço")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Serviço encontrado"),
            @ApiResponse(code = 404, message = "Serviço não encontrado")
    })
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Servico> servico = service.getServicoById(id);
        if (!servico.isPresent()) {
            return new ResponseEntity("Servico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(servico.map(ServicoDTO::create));
    }

    @PostMapping()
    @ApiOperation("Salva um nova Serviço ")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Serviço salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro ao salvar uma Serviço")
    })
    public ResponseEntity post(@RequestBody ServicoDTO dto) {
        try {
            Servico servico = converter(dto);
            servico = service.salvar(servico);
            return new ResponseEntity(servico, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id,@RequestBody ServicoDTO dto) {
        if (!service.getServicoById(id).isPresent()) {
            return new ResponseEntity("Serviço não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Servico servico = converter(dto);
            servico.setId(id);
            service.salvar(servico);
            return ResponseEntity.ok(servico);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Servico> servico = service.getServicoById(id);
        if (!servico.isPresent()) {
            return new ResponseEntity("Serviço não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(servico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Servico converter(ServicoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Servico servico = modelMapper.map(dto, Servico.class);
        if (dto.getIdCargo() != null) {
            Optional<Cargo> cargos = cargoService.getCargoById(dto.getIdCargo());
            if (!cargos.isPresent()) {
                servico.setCargo(null);
            } else {
                servico.setCargo(cargos.get());
            }
        }
        return servico;
    }
}
