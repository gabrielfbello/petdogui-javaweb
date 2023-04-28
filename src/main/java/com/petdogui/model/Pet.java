package com.petdogui.model;

import com.petdogui.model.enums.GeneroEnum;
import com.petdogui.model.enums.PorteEnum;

public class Pet {
    private int id;
    private String nome;
    private Dono dono;
    private GeneroEnum genero;
    private double peso;
    private PorteEnum porte;

    public Pet() {
    }

    public Pet(int id, String nome, Dono dono, GeneroEnum genero, double peso, PorteEnum porte) {
        this.id = id;
        this.nome = nome;
        this.dono = dono;
        this.genero = genero;
        this.peso = peso;
        this.porte = porte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public PorteEnum getPorte() {
        return porte;
    }

    public void setPorte(PorteEnum porte) {
        this.porte = porte;
    }
}