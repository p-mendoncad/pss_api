package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.model.entity.Raca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PetDTO {
    private Long id;
    private String nome;
    private Raca raca;
    private float peso;
    private String dataNasc;
    private String histoVac;
    private boolean sexo;
    private String histServ;
    private String obs;
    private Cliente cliente;

    public static PetDTO create(Pet pet){
        ModelMapper modelMapper = new ModelMapper();
        PetDTO dto = modelMapper.map(pet, PetDTO.class);

        return dto;
    }
}

