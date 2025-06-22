package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Cargo;
import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Pet;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.pssapi.api.dto.ClienteDTO;
import com.example.pssapi.service.ClienteService;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor

public class ClienteController {

    private final ClienteService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Cliente> clientes = service.getClientes();
        return ResponseEntity.ok(clientes.stream().map(ClienteDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = service.getClienteById(id);
        if (!cliente.isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cliente.map(ClienteDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(ClienteDTO dto) {
        try {
            Cliente cliente = converter(dto);
            cliente = service.salvar(cliente);
            return new ResponseEntity(cliente, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, ClienteDTO dto) {
        if (!service.getClienteById(id).isPresent()) {
            return new ResponseEntity("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Cliente cliente = converter(dto);
            cliente.setId(id);
            service.salvar(cliente);
            return ResponseEntity.ok(cliente);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Cliente converter(ClienteDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Cliente.class);
    }
}
