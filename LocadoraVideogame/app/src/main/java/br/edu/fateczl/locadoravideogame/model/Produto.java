package br.edu.fateczl.locadoravideogame.model;

public abstract class Produto {

    private int codigo;
    private String nome;
    private float preco;

    public Produto() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - " + codigo + " - $" + preco;
    }
}
