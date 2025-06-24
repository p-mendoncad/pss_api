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


    private PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
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

    public void validar(Pet Pet) {
        if (Pet.getNome() == null || Pet.getNome().trim().equals("")) {
            throw new RegraNegocioException("Pet inválido");
        }
    }
}
