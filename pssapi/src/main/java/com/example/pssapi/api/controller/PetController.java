package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.PetDTO;
import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.service.ClienteService;
import com.example.pssapi.service.PetService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class PetController {

    private final PetService service;
    private final ClienteService clienteService;



    public Pet converter(PetDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Pet Pet = modelMapper.map(dto, Pet.class);
        if (dto.getIdCliente() != null) {
            Optional<Cliente> Cliente = ClienteService.getClienteById(dto.getIdCliente());
            if (!Cliente.isPresent()) {
                Pet.setCliente(null);
            } else {
                Pet.setCliente(Cliente.get());
            }
        }
        return Pet;
    }
}
