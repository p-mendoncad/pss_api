package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ServicoDTO {
    private Long id;
    private Long idCargo;
    private String Nome;
    private BigDecimal preco;
    private int duracaoMinutos;

    public static ServicoDTO create(Servico servico){
        ModelMapper modelMapper = new ModelMapper();
        ServicoDTO dto = modelMapper.map(servico, ServicoDTO.class);

        return dto;
    }
}

