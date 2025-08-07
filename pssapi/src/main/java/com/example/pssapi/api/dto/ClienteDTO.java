package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String cep;
    private String email;
    private String dataNasc;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;

    private List<PetDTO> pets;

    public static ClienteDTO create(Cliente cliente){
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);

        if(cliente.getPets() != null) {
            dto.pets = cliente.getPets().stream()
                    .map(PetDTO::create)
                    .collect(Collectors.toList());
        }
        return dto;
    }
}

