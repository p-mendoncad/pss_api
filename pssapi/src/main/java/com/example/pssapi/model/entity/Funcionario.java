package com.example.pssapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Funcionario extends Pessoa {
    @ManyToOne
    private Cargo cargo;
}
