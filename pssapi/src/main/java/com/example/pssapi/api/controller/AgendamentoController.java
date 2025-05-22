package com.example.pssapi.api.controller;

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


import java.util.Optional;

@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor

public class AgendamentoController {

    private final Agendamento service;
    private final PetService disciplinaService;

    public Agendamento converter(AgendamentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Agendamento Agendamento = modelMapper.map(dto, Agendamento.class);
        if (dto.getIdServico() != null) {
            Optional<Servico> Servico = ServicoService.getServicoById(dto.getIdServico());
            if (!Servico.isPresent()) {
                Agendamento.setServico(null);
            } else {
                Agendamento.setServico(Servico.get());
            }
            if (dto.getIdPet() != null) {
                Optional<Pet> Pet = ServicoService.getPetById(dto.getIdPet());
                if (!Pet.isPresent()) {
                    Agendamento.setPet(null);
                } else {
                    Agendamento.setPet(Pet.get());
                }
            }
            return Agendamento;
        }
        return Agendamento;
    }
}