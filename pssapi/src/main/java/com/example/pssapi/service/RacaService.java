package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.*;
import com.example.pssapi.model.repository.RacaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service


public class RacaService {

        private RacaRepository repository;

        public RacaService(RacaRepository repository) {

            this.repository = repository;
        }

        public List<Raca> getRacas() {
            return repository.findAll();
        }

        public Optional<Raca> getRacaById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Raca salvar(Raca raca) {
            validar(raca);
            return repository.save(raca);
        }

        @Transactional
        public void excluir(Raca raca) {
            Objects.requireNonNull(raca.getId());
            repository.delete(raca);
        }

        public void validar(Raca raca) {
            if (raca.getNome() == null || raca.getNome().trim().equals("")) {
                throw new RegraNegocioException("Raça inválida: A raça deve possuir um nome.");
            }
        }
    }
