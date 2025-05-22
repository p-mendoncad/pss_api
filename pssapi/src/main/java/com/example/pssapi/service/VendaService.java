package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Venda;
import com.example.pssapi.model.repository.VendaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaService {

        private VendaRepository repository;

        public VendaService(VendaRepository repository) {
            this.repository = repository;
        }

        public List<Venda> getVendas() {
            return repository.findAll();
        }

        public Optional<Venda> getVendaById(Long id) {
            return repository.findById(id);
        }

        @Transactional
        public Venda salvar(Venda Venda) {
            validar(Venda);
            return repository.save(Venda);
        }

        @Transactional
        public void excluir(Venda Venda) {
            Objects.requireNonNull(Venda.getId());
            repository.delete(Venda);
        }

        public void validar(Venda Venda) {
            if (Venda.getId() == null || Venda.getId().trim().equals("")) {
                throw new RegraNegocioException("Venda inválido");
            }
            if (Venda.getNome() == null || Venda.getNome().trim().equals("")) {
                throw new RegraNegocioException("Venda inválido");
            }
        }
    }
}
