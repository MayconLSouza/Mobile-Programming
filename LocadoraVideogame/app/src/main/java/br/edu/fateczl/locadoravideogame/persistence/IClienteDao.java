package br.edu.fateczl.locadoravideogame.persistence;

import java.sql.SQLException;

public interface IClienteDao {

    public ClienteDao open() throws SQLException;
    public void close();
}
