package com.example.pssapi.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String data;
    private String horaEntrada;
    private String horaSaida;
    private String horario;
    @ManyToOne
    private Servico servico;
    @ManyToOne
    private Pet pet;
    @ManyToOne
    private Funcionario funcionario;
}
