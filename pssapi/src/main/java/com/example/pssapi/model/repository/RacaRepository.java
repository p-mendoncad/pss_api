package com.example.pssapi.model.repository;

import com.example.pssapi.model.entity.Raca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RacaRepository extends JpaRepository<Raca, Long> {

}