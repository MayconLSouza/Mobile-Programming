package br.edu.fateczl.calcsalario2.model;

public class Supervisor extends Funcionario{

    public Supervisor(){
        super();
    }

    @Override
    public double calcSalario(int horas) {
        return 35.43 * horas;
    }

    @Override
    public double calcBonus() {
        return 300.00d;
    }
}
