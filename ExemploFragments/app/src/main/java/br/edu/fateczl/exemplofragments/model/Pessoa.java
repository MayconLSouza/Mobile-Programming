package br.edu.fateczl.exemplofragments.model;

import androidx.annotation.NonNull;

public abstract class Pessoa {

    private String nome;

    public Pessoa() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
