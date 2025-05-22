package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Fornecedor;
import com.example.pssapi.model.repository.FornecedorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service


public class FornecedorService {

        private FornecedorRepository repository;

        public FornecedorService(FornecedorRepository repository) {
            this.repository = repository;
        }

        public List<Fornecedor> getFornecedors() {
            return repository.findAll();
        }

        public Optional<Fornecedor> getFornecedorById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Fornecedor salvar(Fornecedor Fornecedor) {
            validar(Fornecedor);
            return repository.save(Fornecedor);
        }

        @Transactional
        public void excluir(Fornecedor Fornecedor) {
            Objects.requireNonNull(Fornecedor.getId());
            repository.delete(Fornecedor);
        }

        public void validar(Fornecedor Fornecedor) {
            if (Fornecedor.getId() == null || Fornecedor.getId().trim().equals("")) {
                throw new RegraNegocioException("Fornecedor inválido");
            }
            if (Fornecedor.getNome() == null || Fornecedor.getNome().trim().equals("")) {
                throw new RegraNegocioException("Fornecedor inválido");
            }
        }

}
