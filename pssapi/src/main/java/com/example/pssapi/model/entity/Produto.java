package com.example.pssapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Setor setor;
}
