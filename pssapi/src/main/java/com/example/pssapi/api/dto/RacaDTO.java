package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Raca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RacaDTO {
    private Long id;
    private String nome;

    public static RacaDTO create(Raca raca){
        ModelMapper modelMapper = new ModelMapper();
        RacaDTO dto = modelMapper.map(raca, RacaDTO.class);

        return dto;
    }
}

