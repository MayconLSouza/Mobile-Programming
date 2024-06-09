package br.edu.fateczl.biblioteca.persistence;

import java.sql.SQLException;

public interface IAlunoDao {

    public AlunoDao open() throws SQLException;
    public void close();

}
