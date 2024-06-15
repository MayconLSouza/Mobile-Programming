package br.edu.fateczl.locadoravideogame.persistence;

import java.sql.SQLException;

public interface IConsoleDao {

    public ConsoleDao open() throws SQLException;
    public void close();
}
