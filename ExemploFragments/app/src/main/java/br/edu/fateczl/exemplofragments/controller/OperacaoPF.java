package br.edu.fateczl.exemplofragments.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.exemplofragments.model.PessoaFisica;

public class OperacaoPF implements IOperacao<PessoaFisica> {

    private List<PessoaFisica> lista;

    public OperacaoPF(){
        this.lista =new ArrayList<>();
    }

    @Override
    public void cadastrar(PessoaFisica pessoaFisica) {
        lista.add(pessoaFisica);
    }

    @Override
    public List<PessoaFisica> listar() {
        return this.lista;
    }
}
