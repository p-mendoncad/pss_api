package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Raca;
import com.example.pssapi.model.repository.RacaRepository;
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
        public Raca salvar(Raca Raca) {
            validar(Raca);
            return repository.save(Raca);
        }

        @Transactional
        public void excluir(Raca Raca) {
            Objects.requireNonNull(Raca.getId());
            repository.delete(Raca);
        }

        public void validar(Raca Raca) {
            if (Raca.getId() == null || Raca.getId().trim().equals("")) {
                throw new RegraNegocioException("Raca inválido");
            }
            if (Raca.getNome() == null || Raca.getNome().trim().equals("")) {
                throw new RegraNegocioException("Raca inválido");
            }
        }
    }
}
