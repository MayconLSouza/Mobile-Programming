package br.edu.fateczl.locadoravideogame.model;

public class Console extends Produto{

    private String fabricante;

    public Console() {
        super();
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return fabricante + getNome() + " - " + getCodigo() + " - $" + getPreco();
    }
}
