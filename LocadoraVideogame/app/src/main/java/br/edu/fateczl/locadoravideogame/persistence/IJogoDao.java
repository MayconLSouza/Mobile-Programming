package br.edu.fateczl.locadoravideogame.persistence;

import java.sql.SQLException;

public interface IJogoDao {

    public JogoDao open() throws SQLException;
    public void close();
}
