package br.edu.fateczl.professorsalario.model;

public class ProfessorTitular extends Professor {

    private int anosInstituicao;
    private double salario = 3000.00d;

    public ProfessorTitular(){
        super();
    }

    public int getAnosInstituicao() {
        return anosInstituicao;
    }

    public void setAnosInstituicao(int anosInstituicao) {
        this.anosInstituicao = anosInstituicao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double calcularSalario(int anosInstituicao){
        if(anosInstituicao < 5){
            return salario;
        } else {
            return salario + (salario * (((double) anosInstituicao / 5) * 0.05));
        }
    }
}
