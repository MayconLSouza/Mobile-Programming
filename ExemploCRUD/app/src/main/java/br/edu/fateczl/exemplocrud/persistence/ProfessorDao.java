package br.edu.fateczl.exemplocrud.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.exemplocrud.model.Professor;

public class ProfessorDao implements IProfessorDao, ICRUDDao<Professor>{
    
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;
    
    public ProfessorDao(Context context){
        this.context = context;
    }
    
    @Override
    public ProfessorDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Professor professor) throws SQLException {
        ContentValues contentValues = getContentValues(professor);
        database.insert("professor", null, contentValues);
    }

    @Override
    public int update(Professor professor) throws SQLException {
        ContentValues contentValues = getContentValues(professor);
        int ret = database.update("professor", contentValues, "codigo = " + professor.getCodigo(), null);
        return ret;
    }

    @Override
    public void delete(Professor professor) throws SQLException {
        database.delete("professor","codigo = " + professor.getCodigo(),null);
    }

    @SuppressLint("Range")
    @Override
    public Professor findOne(Professor professor) throws SQLException {
        String sql = "SELECT codigo, nome, titulacao FROM professor WHERE codigo = " + professor.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            professor.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            professor.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            professor.setTitulacao(cursor.getString(cursor.getColumnIndex("titulacao")));
        }
        cursor.close();
        return professor;
    }

    @SuppressLint("Range")
    @Override
    public List<Professor> findAll() throws SQLException {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT codigo, nome, titulacao FROM professor";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Professor professor = new Professor();
            professor.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            professor.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            professor.setTitulacao(cursor.getString(cursor.getColumnIndex("titulacao")));

            professores.add(professor);
            cursor.moveToNext();
        }
        cursor.close();
        return professores;
    }

    private static ContentValues getContentValues(Professor professor) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", professor.getCodigo());
        contentValues.put("nome", professor.getNome());
        contentValues.put("titulacao", professor.getTitulacao());

        return contentValues;
    }
}
