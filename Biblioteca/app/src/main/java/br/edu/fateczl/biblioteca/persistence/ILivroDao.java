package br.edu.fateczl.biblioteca.persistence;

import java.sql.SQLException;

import br.edu.fateczl.biblioteca.model.Livro;

public interface ILivroDao {

    public LivroDao open() throws SQLException;
    public void close();

}
