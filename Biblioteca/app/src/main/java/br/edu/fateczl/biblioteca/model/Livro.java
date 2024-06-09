package br.edu.fateczl.biblioteca.model;

public class Livro extends Exemplar{

    private String isbn;
    private int edicao;

    public Livro() {
        super();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return getCodigo() + " - " + getNome() + " - " + getQtdPaginas() + " - " + isbn + " - " + edicao;
    }
}
