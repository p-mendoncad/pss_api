package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Setor;
import com.example.pssapi.model.repository.SetorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SetorService {

        private SetorRepository repository;

        public SetorService(SetorRepository repository) {
            this.repository = repository;
        }

        public List<Setor> getSetors() {
            return repository.findAll();
        }

        public Optional<Setor> getSetorById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Setor salvar(Setor Setor) {
            validar(Setor);
            return repository.save(Setor);
        }

        @Transactional
        public void excluir(Setor Setor) {
            Objects.requireNonNull(Setor.getId());
            repository.delete(Setor);
        }

        public void validar(Setor Setor) {
            if (Setor.getId() == null || Setor.getId().trim().equals("")) {
                throw new RegraNegocioException("Setor inválido");
            }
            if (Setor.getNome() == null || Setor.getNome().trim().equals("")) {
                throw new RegraNegocioException("Setor inválido");
            }
        }
    }
}
