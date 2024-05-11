package br.edu.fateczl.calcsalario.model;

public class Venderdor extends Funcionario{

    public Venderdor(){
        super();
    }

    @Override
    public double calcSalario(int horas) {
        return 20.37 * horas;
    }

    @Override
    public double calcBonus() {
        return 100.00d;
    }
}
