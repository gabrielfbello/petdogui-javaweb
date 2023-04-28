package com.petdogui.service;

import com.petdogui.model.Pet;
import com.petdogui.service.repository.PetRepository;

import java.sql.SQLException;
import java.util.List;

public class PetService {

    private final PetRepository repository;

    public PetService() {
        this.repository = new PetRepository();
    }

    public Pet insert(Pet pet) {
        try {
            return repository.insert(pet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet update(Pet pet) {
        try {
            return repository.update(pet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            repository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet findById(int id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pet> findAll() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}