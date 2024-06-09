package br.edu.fateczl.biblioteca.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.biblioteca.model.Aluguel;
import br.edu.fateczl.biblioteca.model.Aluno;
import br.edu.fateczl.biblioteca.model.Exemplar;
import br.edu.fateczl.biblioteca.model.Livro;
import br.edu.fateczl.biblioteca.model.Revista;

public class AluguelDao implements IAluguelDao, ICRUDDao<Aluguel>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public AluguelDao(Context context){
        this.context = context;
    }

    @Override
    public AluguelDao open() throws SQLException {
        gDao = new GenericDao(context);
        db = gDao.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Aluguel aluguel) throws SQLException {
        ContentValues contentValues = getContentValues(aluguel);
        db.insert("aluguel", null, contentValues);
    }

    @Override
    public int update(Aluguel aluguel) throws SQLException {
        ContentValues contentValues = getContentValues(aluguel);
        String whereClause = "exemplar_codigo = ? AND aluno_ra = ? AND data_retirada = ?";
        String[] whereArgs = new String[]{String.valueOf(aluguel.getExemplar().getCodigo()), String.valueOf(aluguel.getAluno().getRa()), aluguel.getDataRetirada()};
        return db.update("aluguel", contentValues, whereClause, whereArgs);
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        String whereClause = "exemplar_codigo = ? AND aluno_ra = ? AND data_retirada = ?";
        String[] whereArgs = new String[]{String.valueOf(aluguel.getExemplar().getCodigo()), String.valueOf(aluguel.getAluno().getRa()), aluguel.getDataRetirada().toString()};
        db.delete("aluguel", whereClause, whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        String sql = "SELECT aluguel.data_retirada as data_retirada, aluguel.data_devolucao as data_devolucao, " +
                "aluno.ra as ra, aluno.nome as nome_aluno, aluno.email as email, " +
                "exemplar.codigo as codigo, exemplar.nome as exemplar_nome, exemplar.qtd_paginas as qtd_paginas, " +
                "livro.isbn as isbn, livro.edicao as edicao, revista.issn as issn " +
                "FROM aluguel INNER JOIN aluno ON aluguel.aluno_ra = aluno.ra " +
                "INNER JOIN exemplar ON aluguel.exemplar_codigo = exemplar.codigo " +
                "LEFT JOIN revista ON exemplar.codigo = revista.exemplar_codigo " +
                "LEFT JOIN livro ON exemplar.codigo = livro.exemplar_codigo " +
                "WHERE aluguel.exemplar_codigo = ? AND aluguel.aluno_ra = ? AND aluguel.data_retirada = ? ";
        String[] selectionArgs = new String[]{String.valueOf(aluguel.getExemplar().getCodigo()), String.valueOf(aluguel.getAluno().getRa()), aluguel.getDataRetirada().toString()};

        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Aluno aluno = new Aluno();
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome_aluno")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluguel.setAluno(aluno);

            if(!cursor.isNull(cursor.getColumnIndex("isbn"))){
                Livro livro = new Livro();
                livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                livro.setNome(cursor.getString(cursor.getColumnIndex("exemplar_nome")));
                livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
                livro.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
                livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
                aluguel.setExemplar(livro);
            } else {
                Revista revista = new Revista();
                revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                revista.setNome(cursor.getString(cursor.getColumnIndex("exemplar_nome")));
                revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
                revista.setIssn(cursor.getString(cursor.getColumnIndex("issn")));
                aluguel.setExemplar(revista);
            }

            aluguel.setDataRetirada(cursor.getString(cursor.getColumnIndex("data_retirada")));
            aluguel.setDataDevolucao(cursor.getString(cursor.getColumnIndex("data_devolucao")));
        }

        cursor.close();
        return aluguel;
    }

    @SuppressLint("Range")
    @Override
    public List<Aluguel> findAll() throws SQLException {
        List<Aluguel> alugueis = new ArrayList<>();

        String sql = "SELECT aluguel.data_retirada as data_retirada, aluguel.data_devolucao as data_devolucao, " +
                "aluno.ra as ra, aluno.nome as nome_aluno, aluno.email as email, " +
                "exemplar.codigo as codigo, exemplar.nome as exemplar_nome, exemplar.qtd_paginas as qtd_paginas, " +
                "livro.isbn as isbn, livro.edicao as edicao, revista.issn as issn " +
                "FROM aluguel INNER JOIN aluno ON aluguel.aluno_ra = aluno.ra " +
                "INNER JOIN exemplar ON aluguel.exemplar_codigo = exemplar.codigo " +
                "LEFT JOIN revista ON exemplar.codigo = revista.exemplar_codigo " +
                "LEFT JOIN livro ON exemplar.codigo = livro.exemplar_codigo ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Aluno aluno = new Aluno();
            aluno.setRa(cursor.getInt(cursor.getColumnIndex("ra")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome_aluno")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            Aluguel aluguel = new Aluguel();
            aluguel.setAluno(aluno);

            if(!cursor.isNull(cursor.getColumnIndex("isbn"))){
                Livro livro = new Livro();
                livro.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                livro.setNome(cursor.getString(cursor.getColumnIndex("exemplar_nome")));
                livro.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
                livro.setIsbn(cursor.getString(cursor.getColumnIndex("isbn")));
                livro.setEdicao(cursor.getInt(cursor.getColumnIndex("edicao")));
                aluguel.setExemplar(livro);
            } else {
                Revista revista = new Revista();
                revista.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                revista.setNome(cursor.getString(cursor.getColumnIndex("exemplar_nome")));
                revista.setQtdPaginas(cursor.getInt(cursor.getColumnIndex("qtd_paginas")));
                revista.setIssn(cursor.getString(cursor.getColumnIndex("issn")));
                aluguel.setExemplar(revista);
            }

            aluguel.setDataRetirada(cursor.getString(cursor.getColumnIndex("data_retirada")));
            aluguel.setDataDevolucao(cursor.getString(cursor.getColumnIndex("data_devolucao")));
            alugueis.add(aluguel);
            cursor.moveToNext();
        }

        cursor.close();
        return alugueis;
    }

    private static ContentValues getContentValues(Aluguel aluguel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("aluno_ra", aluguel.getAluno().getRa());
        contentValues.put("exemplar_codigo", aluguel.getExemplar().getCodigo());
        contentValues.put("data_retirada", aluguel.getDataRetirada());
        contentValues.put("data_devolucao", aluguel.getDataDevolucao());
        return contentValues;
    }
}
