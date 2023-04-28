package com.petdogui.service;

import com.petdogui.model.Dono;
import com.petdogui.repository.DonoRepository;

import java.util.List;

public class DonoService {

    private DonoRepository donoRepository;

    public DonoService() {
        donoRepository = new DonoRepository();
    }

    public Dono createDono(Dono dono) throws Exception {
        validateDono(dono);
        return donoRepository.save(dono);
    }

    public Dono updateDono(Dono dono) throws Exception {
        validateDono(dono);
        return donoRepository.update(dono);
    }

    public void deleteDono(long id) throws Exception {
        donoRepository.delete(id);
    }

    public Dono findDonoById(long id) throws Exception {
        return donoRepository.findById(id);
    }

    public List<Dono> findAllDonos() throws Exception {
        return donoRepository.findAll();
    }

    private void validateDono(Dono dono) throws Exception {
        if (dono.getNome() == null || dono.getNome().isEmpty()) {
            throw new Exception("Nome do dono não informado.");
        }

        if (dono.getCpf() == null || dono.getCpf().isEmpty()) {
            throw new Exception("CPF do dono não informado.");
        }

        if (dono.getTelefone() == null || dono.getTelefone().isEmpty()) {
            throw new Exception("Telefone do dono não informado.");
        }

        if (dono.getEmail() == null || dono.getEmail().isEmpty()) {
            throw new Exception("E-mail do dono não informado.");
        }

        if (dono.getEndereco() == null || dono.getEndereco().isEmpty()) {
            throw new Exception("Endereço do dono não informado.");
        }
    }
}