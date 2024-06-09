package br.edu.fateczl.biblioteca.controller;

import java.sql.SQLException;
import java.util.List;
import br.edu.fateczl.biblioteca.model.Livro;
import br.edu.fateczl.biblioteca.persistence.LivroDao;

public class LivroController implements IController<Livro> {

    private final LivroDao lDao;

    public LivroController(LivroDao lDao){
        this.lDao = lDao;
    }

    @Override
    public void inserir(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        lDao.insert(livro);
        lDao.close();
    }

    @Override
    public void modificar(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        lDao.update(livro);
        lDao.close();
    }

    @Override
    public void deletar(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        lDao.delete(livro);
        lDao.close();
    }

    @Override
    public Livro buscar(Livro livro) throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        return lDao.findOne(livro);
    }

    @Override
    public List<Livro> listar() throws SQLException {
        if(lDao.open() == null){
            lDao.open();
        }
        return lDao.findAll();
    }
}
