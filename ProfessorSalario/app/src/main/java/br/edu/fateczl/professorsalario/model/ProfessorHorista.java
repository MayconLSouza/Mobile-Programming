package br.edu.fateczl.professorsalario.model;

public class ProfessorHorista extends Professor{

    private int horasAula;
    private double valorHoraAula = 50.00d;

    public ProfessorHorista(){
        super();
    }

    public int getHorasAula() {
        return horasAula;
    }

    public void setHorasAula(int horasAula) {
        this.horasAula = horasAula;
    }

    public double getValorHoraAula() {
        return valorHoraAula;
    }

    public void setValorHoraAula(double valorHoraAula) {
        this.valorHoraAula = valorHoraAula;
    }

    public double calcularSalario(int horas){
        return horas * valorHoraAula;
    }
}
