package br.edu.fateczl.contabancaria.model;

import androidx.annotation.NonNull;

public class ContaPoupanca extends ContaBancaria{

    private int diaRendimento;



    public ContaPoupanca(String cliente, int numConta, float saldo, int diaRendimento){
        setCliente(cliente);
        setNumConta(numConta);
        if(saldo < 0){
            setSaldo(0);
        } else{
            setSaldo(saldo);
        }
        this.diaRendimento = diaRendimento;
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    @Override
    public String sacar(float valorSaque) {
        if((getSaldo() - valorSaque) < 0.0f) {
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

    public String calcularNovoSaldo(double taxaRendimento){
        float novoSaldo = (float) ((getSaldo() * taxaRendimento) + getSaldo());
        setSaldo(novoSaldo);
        return "Seu novo saldo a partir da taxa de rendimento " + taxaRendimento + "% é $ " + getSaldo();
    }

    @Override
    public String toString() {
        return "Cliente: " + getCliente() + "\nConta: " + getNumConta() + "\nDia de rendimento: " + diaRendimento + "\nSaldo: $ " + getSaldo();
    }
}
