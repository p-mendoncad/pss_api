package com.example.pssapi.service;


import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Agendamento;
import com.example.pssapi.model.repository.AgendamentoRepository;

import com.example.scaapi.model.entity.*;
        import com.example.scaapi.model.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgendamentoService {

    private AgendamentoRepositoryRepository repository;

    public AgendamentoServiceService(AgendamentoRepositoryRepository repository) {
        this.repository = repository;
    }

    public List<Agendamento> getAgendamentos() {
        return repository.findAll();
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Agendamento salvar(Agendamento aluno) {
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
        if (aluno.getNome() == null || aluno.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (aluno.getCurso() == null || aluno.getCurso().getId() == null || aluno.getCurso().getId() == 0) {
            throw new RegraNegocioException("Curso inválido");
        }
    }
}{
}
