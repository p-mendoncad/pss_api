package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

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

    public static ClienteDTO create(Cliente cliente){
        ModelMapper modelMapper = new ModelMapper();
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);

        return dto;
    }
}

