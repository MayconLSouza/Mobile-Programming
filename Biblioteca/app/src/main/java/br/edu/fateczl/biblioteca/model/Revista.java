package br.edu.fateczl.biblioteca.model;

public class Revista extends Exemplar{

    private String issn;

    public Revista() {
        super();
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Override
    public String toString() {
        return getCodigo() + " - " + getNome() + " - " + getQtdPaginas() + " - " + issn;
    }
}
