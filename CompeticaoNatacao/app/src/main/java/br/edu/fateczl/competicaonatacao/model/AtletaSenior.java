package br.edu.fateczl.competicaonatacao.model;

public class AtletaSenior extends Atleta{

    private boolean problemasCardiacos;

    public AtletaSenior() {
        super();
    }

    public boolean isProblemasCardiacos() {
        return problemasCardiacos;
    }

    public void setProblemasCardiacos(boolean problemasCardiacos) {
        this.problemasCardiacos = problemasCardiacos;
    }


    @Override
    public String toString() {
        return "AtletaSenior{" +
                " nome = " + getNome() + + '\'' +
                ", data de nascimento = " + getDataNasc() + + '\'' +
                ", bairro = " + getBairro() + + '\'' +
                "problemasCardiacos=" + problemasCardiacos +
                '}';
    }
}
