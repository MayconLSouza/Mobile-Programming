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
import br.edu.fateczl.biblioteca.model.Revista;

public class RevistaDao implements IRevistaDao, ICRUDDao<Revista> {

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public RevistaDao(Context context){
        this.context = context;
    }

    @Override
    public RevistaDao open() throws SQLException {
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
    public void insert(Revista revista) throws SQLException {
        db.insert("exemplar", null, getContentValues(revista, true));
        db.insert("revista", null, getContentValues(revista, false));
    }

    @Override
    public int update(Revista revista) throws SQLException {
        db.update("exemplar", getContentValues(revista, true), "codigo = " + revista.getCodigo(), null);
        return db.update("revista", getContentValues(revista, false), "exemplar_codigo = " + revista.getCodigo(), null);
    }

    @Override
    public void delete(Revista revista) throws SQLException {
        db.delete("exemplar", "codigo = " + revista.getCodigo(), null);
        db.delete("revista","exemplar_codigo" + revista.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Revista findOne(Revista revista) throws SQLException {
        String sql = "SELECT exemplar.codigo, exemplar.nome, exemplar.qtd_paginas, revista.issn" +
                "FROM exemplar, revista " +
                "WHERE exemplar.codigo = revista.exemplar_codigo AND exemplar.codigo = " + revista.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            revista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
            revista.setIssn(cursor.getString(cursor.getColumnIndex("issn")));
        }
        cursor.close();
        return revista;
    }

    @SuppressLint("Range")
    @Override
    public List<Revista> findAll() throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        String sql = "SELECT exemplar.codigo, exemplar.nome, exemplar.qtd_paginas, revista.issn " +
                "FROM exemplar, revista " +
                "WHERE exemplar.codigo = revista.exemplar_codigo";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Revista revista = new Revista();
            revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            revista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
            revista.setIssn(cursor.getString(cursor.getColumnIndex("issn")));

            revistas.add(revista);
            cursor.moveToNext();
        }
        cursor.close();
        return revistas;
    }

    private ContentValues getContentValues(Revista revista, boolean isSuper) {
        ContentValues contentValues = new ContentValues();
        if(isSuper){
            contentValues.put("codigo", revista.getCodigo());
            contentValues.put("nome", revista.getNome());
            contentValues.put("qtd_paginas", revista.getQtdPaginas());
        } else {
            contentValues.put("issn", revista.getCodigo());
            contentValues.put("exemplar_codigo", revista.getCodigo());
        }
        return contentValues;
    }
}
