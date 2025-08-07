package com.example.pssapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Pessoa{
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Pet> pets = new ArrayList<>();
}
