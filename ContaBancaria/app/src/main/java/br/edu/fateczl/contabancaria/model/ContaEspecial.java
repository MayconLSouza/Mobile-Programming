package br.edu.fateczl.contabancaria.model;

public class ContaEspecial extends ContaBancaria{

    private float limite;

    public ContaEspecial(String cliente, int numConta, float saldo, int limite){
        setCliente(cliente);
        setNumConta(numConta);
        if(saldo < 0){
            setSaldo(0);
        } else{
            setSaldo(saldo);
        }
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public String sacar(float valorSaque) {
        if ((getSaldo() + limite - valorSaque) < 0) {
            return "Saque no valor de $" + valorSaque + " nao pode ser realizado";
        } else {
            setSaldo(getSaldo() - valorSaque);
            return "Saque no valor de $" + valorSaque + " realizado";
        }
    }

    @Override
    public String depositar(float valorDeposito) {
        setSaldo(getSaldo() + valorDeposito);
        return "Depósito no valor de $" + valorDeposito + " realizado";
    }

    @Override
    public String toString() {
        return "Cliente: " + getCliente() + "\nConta: " + getNumConta() + "\nLimite: $" + limite + "\nSaldo: $ " + getSaldo();
    }
}
