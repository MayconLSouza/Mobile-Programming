package br.edu.fateczl.biblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.biblioteca.model.Aluno;
import br.edu.fateczl.biblioteca.persistence.AlunoDao;

public class AlunoController implements IController<Aluno>{

    private final AlunoDao aDao;

    public AlunoController(AlunoDao aDao){
        this.aDao = aDao;
    }
    @Override
    public void inserir(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.insert(aluno);
        aDao.close();
    }

    @Override
    public void modificar(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.update(aluno);
        aDao.close();
    }

    @Override
    public void deletar(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.delete(aluno);
        aDao.close();
    }

    @Override
    public Aluno buscar(Aluno aluno) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findOne(aluno);
    }

    @Override
    public List<Aluno> listar() throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findAll();
    }
}
