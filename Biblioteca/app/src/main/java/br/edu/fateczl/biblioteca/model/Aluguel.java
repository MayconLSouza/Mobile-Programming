package br.edu.fateczl.biblioteca.model;

public class Aluguel {

    private Aluno aluno;
    private Exemplar exemplar;
    private String dataRetirada;
    private String dataDevolucao;

    public Aluguel() {
        super();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return aluno + " - " + exemplar + " - " + dataRetirada + " - " + dataDevolucao;
    }
}
