package com.example.pssapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Raca raca;
    private float peso;
    private String dataNasc;
    private String histoVac;
    private boolean sexo;
    private String histServ;
    private String obs;
    @ManyToOne
    private Cliente cliente;
}