package br.edu.fateczl.calcsalario2.model;

public class Vendedor extends Funcionario{

    public Vendedor(){
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
