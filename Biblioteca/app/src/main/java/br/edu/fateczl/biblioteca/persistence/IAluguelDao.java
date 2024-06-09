package br.edu.fateczl.biblioteca.persistence;

import java.sql.SQLException;

public interface IAluguelDao {

    public AluguelDao open() throws SQLException;
    public void close();

}
