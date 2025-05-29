package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Produto;
import com.example.pssapi.model.entity.Setor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoDTO {
    private Long id;
    private String nome;
    private String quantidade;
    private String quantidadeMin;
    private String descricao;
    private String precoVenda;
    private Long idSetor;
    private Long idFornecedor;

    public static ProdutoDTO create(Produto produto){
        ModelMapper modelMapper = new ModelMapper();
        ProdutoDTO dto = modelMapper.map(produto, ProdutoDTO.class);

        return dto;
    }
}

