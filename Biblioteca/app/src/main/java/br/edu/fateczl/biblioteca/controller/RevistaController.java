package br.edu.fateczl.biblioteca.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.biblioteca.model.Revista;
import br.edu.fateczl.biblioteca.persistence.AlunoDao;
import br.edu.fateczl.biblioteca.persistence.RevistaDao;

public class RevistaController implements IController<Revista>{

    private final RevistaDao rDao;

    public RevistaController(RevistaDao rDao){
        this.rDao = rDao;
    }

    @Override
    public void inserir(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.insert(revista);
        rDao.close();
    }

    @Override
    public void modificar(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.update(revista);
        rDao.close();
    }

    @Override
    public void deletar(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.delete(revista);
        rDao.close();
    }

    @Override
    public Revista buscar(Revista revista) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        return rDao.findOne(revista);
    }

    @Override
    public List<Revista> listar() throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        return rDao.findAll();
    }
}
