package com.example.pssapi.api.controller;


import com.example.pssapi.api.dto.FuncionarioDTO;
import com.example.pssapi.api.dto.FuncionarioDTO;
import com.example.pssapi.service.FuncionarioService;
import com.example.pssapi.service.FuncionarioService;
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

public class FuncionarioController {

    private final FuncionarioService service;

    public Funcionario converter(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Funcionario.class);

    }
}
