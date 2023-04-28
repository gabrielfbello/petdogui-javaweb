package com.petdogui.service;

import com.petdogui.model.Atendimento;
import com.petdogui.service.repository.AtendimentoRepository;

import java.sql.SQLException;
import java.util.List;

public class AtendimentoService {

    private final AtendimentoRepository repository;

    public AtendimentoService() {
        this.repository = new AtendimentoRepository();
    }

    public Atendimento insert(Atendimento atendimento) {
        try {
            return repository.insert(atendimento);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Atendimento update(Atendimento atendimento) {
        try {
            return repository.update(atendimento);
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

    public Atendimento findById(int id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Atendimento> findAll() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}