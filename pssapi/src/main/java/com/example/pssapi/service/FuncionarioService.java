package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Funcionario;
import com.example.pssapi.model.repository.FuncionarioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

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

    public void validar(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            throw new RegraNegocioException("ID do fornecedor inválido");
        }
        if (fornecedor.getNome() == null || fornecedor.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome do fornecedor inválido");
        }
    }
}
