package br.edu.fateczl.exemplofragments.model;

import androidx.annotation.NonNull;

public class PessoaFisica extends Pessoa{

    private String cpf;
    private String telefone;

    public PessoaFisica() {
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @NonNull
    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                ", nome = " + getNome() + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
