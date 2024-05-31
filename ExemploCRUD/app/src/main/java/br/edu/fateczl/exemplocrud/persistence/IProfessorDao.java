package br.edu.fateczl.exemplocrud.persistence;

import java.sql.SQLException;

import br.edu.fateczl.exemplocrud.model.Professor;

public interface IProfessorDao {

    public ProfessorDao open() throws SQLException;
    public void close();

}
