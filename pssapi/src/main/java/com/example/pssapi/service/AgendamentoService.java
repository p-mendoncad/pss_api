package com.example.pssapi.service;


import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Agendamento;

import com.example.pssapi.model.repository.AgendamentoRepository;
import com.example.pssapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgendamentoService {

    private AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public List<Agendamento> getAgendamentos() {
        return repository.findAll();
    }

    public Optional<Agendamento> getAgendamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Agendamento salvar(Agendamento agendamento) {
        validar(agendamento);
        return repository.save(agendamento);
    }

    @Transactional
    public void excluir(Agendamento agendamento) {
        Objects.requireNonNull(agendamento.getId());
        repository.delete(agendamento);
    }

    public void validar(Agendamento agendamento) {
        if (agendamento.getId() == null || agendamento.getId() == 0) {
            throw new RegraNegocioException("Agendamento inválida");
        }
        if (agendamento.getServico() == null || agendamento.getServico().getId() == null || agendamento.getServico().getId() == 0) {
            throw new RegraNegocioException("Serviço inválido");
        }
        if (agendamento.getPet() == null || agendamento.getPet().getId() == null || agendamento.getPet().getId() == 0) {
            throw new RegraNegocioException("Pet Inexistente");
        }
    }
}