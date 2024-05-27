package br.edu.fateczl.competicaonatacao.model;

public class AtletaPleno extends Atleta{

    private String academia;
    private double recorde;

    public AtletaPleno() {
        super();
    }

    public String getAcademia() {
        return academia;
    }

    public void setAcademia(String academia) {
        this.academia = academia;
    }

    public double getRecorde() {
        return recorde;
    }

    public void setRecorde(double recorde) {
        this.recorde = recorde;
    }

    @Override
    public String toString() {
        return "AtletaPleno{" +
                " nome = " + getNome() + + '\'' +
                ", data de nascimento = " + getDataNasc() + + '\'' +
                ", bairro = " + getBairro() + + '\'' +
                "Academia='" + academia + '\'' +
                ", recorde=" + recorde +
                '}';
    }
}
