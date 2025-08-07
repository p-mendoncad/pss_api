package com.example.pssapi.service;

import com.example.pssapi.exception.RegraNegocioException;
import com.example.pssapi.model.entity.Pet;
import com.example.pssapi.model.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository repository;
    private final ClienteService clienteService;
    private final RacaService racaService;

    public PetService(PetRepository repository,
                      ClienteService clienteService,
                      RacaService racaService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.racaService = racaService;
    }

    public List<Pet> getPets() {
        return repository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pet salvar(Pet pet) {
        validar(pet);
        return repository.save(pet);
    }

    @Transactional
    public void excluir(Pet pet) {
        Objects.requireNonNull(pet.getId());
        repository.delete(pet);
    }

    public void validar(Pet pet) {
        if (pet.getNome() == null || pet.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Nome do pet é obrigatório");
        }

        if (pet.getCliente() == null || pet.getCliente().getId() == null) {
            throw new RegraNegocioException("Cliente é obrigatório");
        }

        if (pet.getRaca() == null || pet.getRaca().getId() == null) {
            throw new RegraNegocioException("Raça é obrigatória");
        }

        // Verify if cliente exists
        if (!clienteService.getClienteById(pet.getCliente().getId()).isPresent()) {
            throw new RegraNegocioException("Cliente não encontrado");
        }

        // Verify if raca exists
        if (!racaService.getRacaById(pet.getRaca().getId()).isPresent()) {
            throw new RegraNegocioException("Raça não encontrada");
        }
    }
}