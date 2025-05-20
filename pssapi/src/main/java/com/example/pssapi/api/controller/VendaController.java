package com.example.pssapi.api.controller;

import com.example.pssapi.api.dto.VendaDTO;
import com.example.pssapi.model.entity.Venda;
import org.modelmapper.ModelMapper;

public class VendaController {
    public Venda converter(VendaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Venda.class);
    }
}
