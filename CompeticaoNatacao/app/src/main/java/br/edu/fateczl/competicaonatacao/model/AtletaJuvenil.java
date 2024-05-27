package br.edu.fateczl.competicaonatacao.model;

public class AtletaJuvenil extends Atleta{

    private int anosPratica;

    public int getAnosPratica() {
        return anosPratica;
    }

    public void setAnosPratica(int anosPratica) {
        this.anosPratica = anosPratica;
    }

    @Override
    public String toString() {
        return "AtletaJuvenil{" +
                " nome = " + getNome() + + '\'' +
                ", data de nascimento = " + getDataNasc() + + '\'' +
                ", bairro = " + getBairro() + + '\'' +
                ", anosPratica=" + anosPratica +
                '}';
    }

}
