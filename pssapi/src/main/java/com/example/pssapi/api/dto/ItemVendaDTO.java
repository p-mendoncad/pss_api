package com.example.pssapi.api.dto;

import com.example.pssapi.model.entity.ItemVenda;
import com.example.pssapi.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaDTO {
    private Long id;
    private Long idVenda;
    private Long idProduto;
    private Integer quantidade;
    private BigDecimal subtotal;

    public static ItemVendaDTO create(ItemVenda itemVenda){
        ModelMapper modelMapper = new ModelMapper();
        ItemVendaDTO dto = modelMapper.map(itemVenda, ItemVendaDTO.class);

        return dto;
    }
}
