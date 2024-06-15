package br.edu.fateczl.locadoravideogame.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Jogo;
import br.edu.fateczl.locadoravideogame.persistence.JogoDao;

public class JogoController implements IController<Jogo> {

    private final JogoDao jDao;

    public JogoController(JogoDao jDao){
        this.jDao = jDao;
    }

    @Override
    public void inserir(Jogo jogo) throws SQLException {
        if(jDao.open() == null){
            jDao.open();
        }
        jDao.insert(jogo);
        jDao.close();
    }

    @Override
    public void modificar(Jogo jogo) throws SQLException {
        if(jDao.open() == null){
            jDao.open();
        }
        jDao.update(jogo);
        jDao.close();
    }

    @Override
    public void deletar(Jogo jogo) throws SQLException {
        if(jDao.open() == null){
            jDao.open();
        }
        jDao.delete(jogo);
        jDao.close();
    }

    @Override
    public Jogo buscar(Jogo jogo) throws SQLException {
        if(jDao.open() == null){
            jDao.open();
        }
        return jDao.findOne(jogo);
    }

    @Override
    public List<Jogo> listar() throws SQLException {
        if ((jDao.open() == null)){
            jDao.open();
        }
        return jDao.findAll();
    }
}
