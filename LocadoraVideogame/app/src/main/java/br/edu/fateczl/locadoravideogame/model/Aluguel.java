package br.edu.fateczl.locadoravideogame.model;

import java.time.LocalDate;

public class Aluguel {

    private Cliente cliente;
    private Produto produto;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;

    public Aluguel() {
        super();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return cliente + " - " + produto + " - " + dataRetirada + " - " + dataDevolucao;
    }
}
