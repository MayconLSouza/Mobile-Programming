package br.edu.fateczl.biblioteca.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.biblioteca.model.Livro;

public class LivroDao implements ILivroDao, ICRUDDao<Livro>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public LivroDao(Context context){
        this.context = context;
    }
    @Override
    public LivroDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Livro livro) throws SQLException {
        db.insert("exemplar", null, getContentValues(livro, true));
        db.insert("livro", null, getContentValues(livro, false));
    }

    @Override
    public int update(Livro livro) throws SQLException {
        db.update("exemplar", getContentValues(livro, true), "codigo = " + livro.getCodigo(), null);
        return db.update("livro", getContentValues(livro, false), "exemplar_codigo = " + livro.getCodigo(), null);
    }

    @Override
    public void delete(Livro livro) throws SQLException {
        db.delete("exemplar", "codigo = " + livro.getCodigo(), null);
        db.delete("livro","exemplar_codigo" + livro.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Livro findOne(Livro livro) throws SQLException {
        String sql = "SELECT exemplar.codigo, exemplar.nome, exemplar.qtd_paginas, livro.isbn, livro.edicao " +
                "FROM exemplar, codigo " +
                "WHERE exemplar.codigo = livro.exemplar_codigo AND exemplar.codigo = " + livro.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            livro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
            livro.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
            livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
        }
        cursor.close();
        return livro;
    }

    @SuppressLint("Range")
    @Override
    public List<Livro> findAll() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT exemplar.codigo, exemplar.nome, exemplar.qtd_paginas, livro.isbn, livro.edicao " +
                "FROM exemplar, livro " +
                "WHERE exemplar.codigo = livro.exemplar_codigo";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Livro livro = new Livro();
            livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            livro.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
            livro.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
            livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));

            livros.add(livro);
            cursor.moveToNext();
        }
        cursor.close();
        return livros;
    }

    private ContentValues getContentValues(Livro livro, boolean isSuper) {
        ContentValues contentValues = new ContentValues();
        if(isSuper){
            contentValues.put("codigo", livro.getCodigo());
            contentValues.put("nome", livro.getNome());
            contentValues.put("qtd_paginas", livro.getQtdPaginas());
        } else {
            contentValues.put("isbn", livro.getCodigo());
            contentValues.put("edicao", livro.getCodigo());
            contentValues.put("exemplar_codigo", livro.getCodigo());
        }
        return contentValues;
    }
}
