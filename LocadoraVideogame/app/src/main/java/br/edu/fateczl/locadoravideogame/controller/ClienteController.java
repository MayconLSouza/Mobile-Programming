package br.edu.fateczl.locadoravideogame.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Cliente;
import br.edu.fateczl.locadoravideogame.persistence.ClienteDao;

public class ClienteController implements IController<Cliente> {

    private final ClienteDao cDao;

    public ClienteController(ClienteDao cDao){
        this.cDao = cDao;
    }

    @Override
    public void inserir(Cliente cliente) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.insert(cliente);
        cDao.close();
    }

    @Override
    public void modificar(Cliente cliente) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.update(cliente);
        cDao.close();
    }

    @Override
    public void deletar(Cliente cliente) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        cDao.delete(cliente);
        cDao.close();
    }

    @Override
    public Cliente buscar(Cliente cliente) throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        return cDao.findOne(cliente);
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        if(cDao.open() == null){
            cDao.open();
        }
        return cDao.findAll();
    }
}
