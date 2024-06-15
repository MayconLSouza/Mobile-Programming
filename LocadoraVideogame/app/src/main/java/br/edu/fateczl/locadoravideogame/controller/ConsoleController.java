package br.edu.fateczl.locadoravideogame.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Console;
import br.edu.fateczl.locadoravideogame.persistence.ConsoleDao;

public class ConsoleController implements IController<Console> {

    private final ConsoleDao cDao;

    public ConsoleController(ConsoleDao cDao){
        this.cDao = cDao;
    }

    @Override
    public void inserir(Console console) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.insert(console);
        cDao.close();
    }

    @Override
    public void modificar(Console console) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.update(console);
        cDao.close();
    }

    @Override
    public void deletar(Console console) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.delete(console);
        cDao.close();
    }

    @Override
    public Console buscar(Console console) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        return cDao.findOne(console);
    }

    @Override
    public List<Console> listar() throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        return cDao.findAll();
    }
}
