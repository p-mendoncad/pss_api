package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Funcionario;
import org.springframework.stereotype.Service;
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

    public void validar(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar um nome.");
        }

        if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar um CPF.");
        }

        if (funcionario.getDataNasc() == null || funcionario.getDataNasc().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar a data de nascimento.");
        }

        if (funcionario.getCep() == null || funcionario.getCep().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar o CEP.");
        }

        if (funcionario.getLogradouro() == null || funcionario.getLogradouro().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar o logradouro.");
        }

        if (funcionario.getNumero() == null || funcionario.getNumero().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar o número.");
        }

        if (funcionario.getBairro() == null || funcionario.getBairro().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar o bairro.");
        }

        if (funcionario.getCidade() == null || funcionario.getCidade().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar a cidade.");
        }

        if (funcionario.getEstado() == null || funcionario.getEstado().trim().isEmpty()) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar o estado.");
        }

        if (funcionario.getCargo() == null) {
            throw new RegraNegocioException("Funcionário inválido: É necessário informar o cargo.");
        }
    }
}