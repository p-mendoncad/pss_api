package com.example.pssapi.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Venda venda;

    @ManyToOne
    private Produto produto;
    private Integer quantidade;
    private BigDecimal subtotal;
}
