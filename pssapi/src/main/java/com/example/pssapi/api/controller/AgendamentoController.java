package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.service.AgendamentoService;
import com.example.pssapi.service.PetService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pssapi.api.dto.AgendamentoDTO;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.model.entity.Servico;
import com.example.pssapi.model.entity.Agendamento;
import com.example.pssapi.service.ServicoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor

public class AgendamentoController {

    private final AgendamentoService service;
    private final PetService petService;
    private final ServicoService servicoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Agendamento> agendamentos = service.getAgendamentos();
        return ResponseEntity.ok(agendamentos.stream().map(AgendamentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if (!agendamento.isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(agendamento.map(AgendamentoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(AgendamentoDTO dto) {
        try {
            Agendamento agendamento = converter(dto);
            agendamento = service.salvar(agendamento);
            return new ResponseEntity(agendamento, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, AgendamentoDTO dto) {
        if (!service.getAgendamentoById(id).isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Agendamento agendamento = converter(dto);
            agendamento.setId(id);
            service.salvar(agendamento);
            return ResponseEntity.ok(agendamento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Agendamento converter(AgendamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Agendamento Agendamento = modelMapper.map(dto, Agendamento.class);
        if (dto.getIdServico() != null) {
            Optional<Servico> servico = servicoService.getServicoById(dto.getIdServico());
            if (!servico.isPresent()) {
                Agendamento.setServico(null);
            } else {
                Agendamento.setServico(servico.get());
            }
        }
        if (dto.getIdPet() != null) {
            Optional<Pet> pet = petService.getPetById(dto.getIdPet());
            if (!pet.isPresent()) {
                Agendamento.setPet(null);
            } else {
                Agendamento.setPet(pet.get());
            }
        }
        return Agendamento;
    }
}