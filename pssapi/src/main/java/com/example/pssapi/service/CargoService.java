package com.example.pssapi.service;


import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Cargo;

import com.example.pssapi.model.repository.CargoRepository;
import com.example.pssapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class CargoService {

    private CargoRepository repository;

    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public List<Cargo> getCargos() {
        return repository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cargo salvar(Cargo Cargo) {
        validar(Cargo);
        return repository.save(Cargo);
    }

    @Transactional
    public void excluir(Cargo Cargo) {
        Objects.requireNonNull(Cargo.getId());
        repository.delete(Cargo);
    }

    public void validar(Cargo Cargo) {
        if (Cargo.getNome() == null || Cargo.getNome().trim().equals("")) {
            throw new RegraNegocioException("Cargo inválido: Cargo precisa ter um nome definido.");
        }
        if (Cargo.getNome().trim().equals("") || Cargo.getSalario() == 0) {
            throw new RegraNegocioException("Cargo inválido: Cargo precisa ter um salário definido.");
        }
    }
}