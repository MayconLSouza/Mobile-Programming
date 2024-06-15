package br.edu.fateczl.locadoravideogame.model;

public class Jogo extends Produto{

    private String desenvolvedora;
    private int idadeRecomendada;

    public Jogo() {
        super();
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public int getIdadeRecomendada() {
        return idadeRecomendada;
    }

    public void setIdadeRecomendada(int idadeRecomendada) {
        this.idadeRecomendada = idadeRecomendada;
    }

    @Override
    public String toString() {
        return getNome() + " - " + getCodigo() + " - $" + getPreco() + " - " + desenvolvedora + " - Para maiores de " + idadeRecomendada + " anos";
    }
}
