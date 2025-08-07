package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Pet;
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
    private Long idRaca;
    private float peso;
    private String dataNasc;
    private String histoVac;
    private boolean sexo;
    private String histServ;
    private String obs;
    private Long idCliente;
    private String nomeRaca;
    private String nomeCliente;

    public static PetDTO create(Pet pet) {
        if (pet == null) return null;

        ModelMapper modelMapper = new ModelMapper();
        PetDTO dto = modelMapper.map(pet, PetDTO.class);

        if (pet.getRaca() != null) {
            dto.setIdRaca(pet.getRaca().getId());
            dto.setNomeRaca(pet.getRaca().getNome());
        }

        if (pet.getCliente() != null) {
            dto.setIdCliente(pet.getCliente().getId());
            dto.setNomeCliente(pet.getCliente().getNome());
        }

        return dto;
    }
}