package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FornecedorDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private String cep;
    
    public static FornecedorDTO create(Fornecedor fornecedor){
        ModelMapper modelMapper = new ModelMapper();
        FornecedorDTO dto = modelMapper.map(fornecedor, FornecedorDTO.class);

        return dto;
    }
}

