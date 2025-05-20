package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VendaDTO {
    private Long id;
    private Long idCliente;

    public static VendaDTO create(Venda venda){
        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);

        return dto;
    }
}

