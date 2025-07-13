package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VendaDTO {
    private Long id;
    private Long idCliente;
    private Long idItemVenda;
    private String nomeProduto;
    private String nomeCliente;
    private Integer quantidadeProduto;
    private BigDecimal subtotalProduto;
    private BigDecimal precoProduto;
    private Long idProduto;

    public static VendaDTO create(Venda venda){
        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);
        dto.nomeProduto = venda.getItemVenda().getProduto().getNome();
        dto.nomeCliente = venda.getCliente().getNome();
        dto.idProduto = venda.getItemVenda().getProduto().getId();
        dto.quantidadeProduto = venda.getItemVenda().getQuantidade();
        dto.subtotalProduto = venda.getItemVenda().getSubtotal();
        dto.precoProduto = venda.getItemVenda().getProduto().getPrecoVenda();

        return dto;
    }
}

