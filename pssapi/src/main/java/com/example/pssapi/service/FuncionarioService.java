package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Funcionario;
import com.example.pssapi.model.repository.FuncionarioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FuncionarioService {

        private FuncionarioRepository repository;

        public FuncionarioService(FuncionarioRepository repository) {
            this.repository = repository;
        }

        public List<Funcionario> getFuncionarios() {
            return repository.findAll();
        }

        public Optional<Funcionario> getFuncionarioById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Funcionario salvar(Funcionario Funcionario) {
            validar(Funcionario);
            return repository.save(Funcionario);
        }

        @Transactional
        public void excluir(Funcionario Funcionario) {
            Objects.requireNonNull(Funcionario.getId());
            repository.delete(Funcionario);
        }

        public void validar(Funcionario Funcionario) {
            if (Funcionario.getId() == null || Funcionario.getId().trim().equals("")) {
                throw new RegraNegocioException("Funcionario inválido");
            }
            if (Funcionario.getNome() == null || Funcionario.getNome().trim().equals("")) {
                throw new RegraNegocioException("Funcionario inválido");
            }
        }
    }
}
