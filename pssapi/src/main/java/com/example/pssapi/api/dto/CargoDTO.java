package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CargoDTO {
    private Long id;
    private String nome;
    private float salario;

    public static CargoDTO create(Cargo cargo){
        ModelMapper modelMapper = new ModelMapper();
        CargoDTO dto = modelMapper.map(cargo, CargoDTO.class);

        return dto;
    }
}

