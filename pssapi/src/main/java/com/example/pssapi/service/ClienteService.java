package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Cliente;
import com.example.pssapi.model.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class ClienteService {

        private ClienteRepository repository;

        public ClienteService(ClienteRepository repository) {
            this.repository = repository;
        }

        public List<Cliente> getClientes() {
            return repository.findAll();
        }

        public Optional<Cliente> getClienteById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Cliente salvar(Cliente Cliente) {
            validar(Cliente);
            return repository.save(Cliente);
        }

        @Transactional
        public void excluir(Cliente Cliente) {
            Objects.requireNonNull(Cliente.getId());
            repository.delete(Cliente);
        }

        public void validar(Cliente Cliente) {
            if (Cliente.getId() == null || Cliente.getId().trim().equals("")) {
                throw new RegraNegocioException("Cliente inválido");
            }
            if (Cliente.getNome() == null || Cliente.getNome().trim().equals("")) {
                throw new RegraNegocioException("Cliente inválido");
            }
        }
    }
