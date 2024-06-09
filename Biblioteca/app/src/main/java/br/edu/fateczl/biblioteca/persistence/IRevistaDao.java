package br.edu.fateczl.biblioteca.persistence;

import java.sql.SQLException;

import br.edu.fateczl.biblioteca.model.Revista;

public interface IRevistaDao {

    public RevistaDao open() throws SQLException;
    public void close();

}
