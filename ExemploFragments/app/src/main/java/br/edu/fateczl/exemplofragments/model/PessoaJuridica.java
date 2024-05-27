package br.edu.fateczl.exemplofragments.model;

import androidx.annotation.NonNull;

public class PessoaJuridica extends Pessoa{

    private String cnpj;
    private String email;

    public PessoaJuridica() {
        super();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "cnpj='" + cnpj + '\'' +
                ", nome = " + getNome() + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
