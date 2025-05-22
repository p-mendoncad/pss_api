package com.example.pssapi.api.controller;

import com.example.pssapi.exception.RegraNegocioException;


import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pssapi.api.dto.CargoDTO;
import com.example.pssapi.service.CargoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/cargos")
@RequiredArgsConstructor

public class CargoController {

    private final CargoService service;

    public Cargo converter(CargoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Cargo.class);

    }
}
