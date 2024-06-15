package br.edu.fateczl.locadoravideogame.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Aluguel;
import br.edu.fateczl.locadoravideogame.persistence.AluguelDao;

public class AluguelController implements IController<Aluguel> {

    private final AluguelDao aDao;

    public AluguelController(AluguelDao aDao){
        this.aDao = aDao;
    }

    @Override
    public void inserir(Aluguel aluguel) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.insert(aluguel);
        aDao.close();
    }

    @Override
    public void modificar(Aluguel aluguel) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.update(aluguel);
        aDao.close();
    }

    @Override
    public void deletar(Aluguel aluguel) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        aDao.delete(aluguel);
        aDao.close();
    }

    @Override
    public Aluguel buscar(Aluguel aluguel) throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findOne(aluguel);
    }

    @Override
    public List<Aluguel> listar() throws SQLException {
        if(aDao.open() == null){
            aDao.open();
        }
        return aDao.findAll();
    }
}
