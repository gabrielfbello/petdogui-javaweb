package com.petdogui.service;

import com.petdogui.model.Animal;
import com.petdogui.repository.AnimalRepository;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService() {
        this.repository = new AnimalRepository();
    }

    public Animal insert(Animal animal) {
        try {
            return repository.insert(animal);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Animal update(Animal animal) {
        try {
            return repository.update(animal);
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

    public Animal findById(int id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Animal> findAll() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
