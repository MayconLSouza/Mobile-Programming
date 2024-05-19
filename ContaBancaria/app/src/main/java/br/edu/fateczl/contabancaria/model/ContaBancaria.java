package br.edu.fateczl.contabancaria.model;

public abstract class ContaBancaria {

    private String cliente;
    private int numConta;
    private float saldo;

    public ContaBancaria() {
        super();
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public abstract String sacar(float valorSaque);
    public abstract String depositar(float valorDeposito);

}
