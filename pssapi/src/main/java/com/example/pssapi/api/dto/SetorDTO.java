package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Setor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SetorDTO {
    private Long id;
    private String nome;

    public static SetorDTO create(Setor setor){
        ModelMapper modelMapper = new ModelMapper();
        SetorDTO dto = modelMapper.map(setor, SetorDTO.class);

        return dto;
    }
}

