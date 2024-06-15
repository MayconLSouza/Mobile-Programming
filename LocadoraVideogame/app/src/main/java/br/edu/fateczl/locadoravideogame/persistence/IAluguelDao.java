package br.edu.fateczl.locadoravideogame.persistence;

import java.sql.SQLException;

public interface IAluguelDao {

    public AluguelDao open() throws SQLException;
    public void close();
}
