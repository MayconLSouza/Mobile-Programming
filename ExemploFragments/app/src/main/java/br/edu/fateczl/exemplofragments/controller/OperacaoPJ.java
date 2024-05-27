package br.edu.fateczl.exemplofragments.controller;

import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.exemplofragments.model.PessoaJuridica;

public class OperacaoPJ implements IOperacao<PessoaJuridica> {

    List<PessoaJuridica> lista;

    public OperacaoPJ() {
        this.lista = new ArrayList<>();
    }

    @Override
    public void cadastrar(PessoaJuridica pessoaJuridica) {
        lista.add(pessoaJuridica);
    }

    @Override
    public List<PessoaJuridica> listar() {
        return this.lista;
    }
}
