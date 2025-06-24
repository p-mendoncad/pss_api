package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Servico;
import com.example.pssapi.model.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class ServicoService {

        private ServicoRepository repository;

        public ServicoService(ServicoRepository repository) {
            this.repository = repository;
        }

        public List<Servico> getServicos() {
            return repository.findAll();
        }

        public Optional<Servico> getServicoById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Servico salvar(Servico Servico) {
            validar(Servico);
            return repository.save(Servico);
        }

        @Transactional
        public void excluir(Servico Servico) {
            Objects.requireNonNull(Servico.getId());
            repository.delete(Servico);
        }

        public void validar(Servico servico) {
            if (servico.getNome() == null || servico.getNome().trim().equals("")) {
                throw new RegraNegocioException("Servico inválido");
            }
        }
    }
