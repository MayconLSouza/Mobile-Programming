package br.edu.fateczl.competicaofutebol.persistence;

import java.sql.SQLException;

public interface IJogadorDao {

    public JogadorDao open() throws SQLException;
    public void close();
}
