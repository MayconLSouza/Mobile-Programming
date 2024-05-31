package br.edu.fateczl.exemplocrud.persistence;

import java.sql.SQLException;

import br.edu.fateczl.exemplocrud.model.Disciplina;

public interface IDisciplinaDao {

    public DisciplinaDao open() throws SQLException;
    public void close();

}
