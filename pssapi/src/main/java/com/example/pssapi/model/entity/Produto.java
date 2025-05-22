package com.example.pssapi.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @ManyToOne
    private Fornecedor fornecedor;

    private String quantidade;
    private String quantidadeMin;
    private String descricao;
    private String validade;
    private String vencimento;
    private String lote;
    private String perecibilidade;
    private String dataEntrada;
    private String codBarras;
    private String precoVenda;
    private String unidadeMedida;
    private String precoCompra;
    @ManyToOne
    private Setor setor;
}
