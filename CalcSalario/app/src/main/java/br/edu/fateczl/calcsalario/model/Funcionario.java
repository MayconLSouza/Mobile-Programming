package br.edu.fateczl.calcsalario.model;

public abstract class Funcionario {

    private String cpf;
    private String nome;

    public Funcionario(){
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract double calcSalario(int horas);
    public abstract double calcBonus();
}
