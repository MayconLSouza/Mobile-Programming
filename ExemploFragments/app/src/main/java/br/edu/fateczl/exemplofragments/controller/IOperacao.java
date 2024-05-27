package br.edu.fateczl.exemplofragments.controller;

import java.util.List;

public interface IOperacao<T> {

    public void cadastrar(T t);
    public List<T> listar();

}
