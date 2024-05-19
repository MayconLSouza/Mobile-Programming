package br.edu.fateczl.vendaingresso.model;

public class IngressoVIP extends Ingresso{
    private String funcao;

    public IngressoVIP() {
        super();
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public float calcularValorFinal(double taxaConveniencia) {
        float valorFinal = (float) ((getValor() * taxaConveniencia) + getValor());
        valorFinal += (float) (valorFinal * 0.18);
        return valorFinal;
    }
}
