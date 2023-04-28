package com.petdogui.model;

public class Animal {
    private int id;
    private String nome;
    private int idade;
    private int donoId;

    public Animal() {
    }

    public Animal(int id, String nome, int idade, int donoId) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.donoId = donoId;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getDonoId() {
        return donoId;
    }

    public void setDonoId(int donoId) {
        this.donoId = donoId;
    }
}