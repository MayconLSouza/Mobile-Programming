package br.edu.fateczl.locadoravideogame.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Cliente;
import br.edu.fateczl.locadoravideogame.model.Jogo;

public class JogoDao implements IJogoDao, ICRUDDao<Jogo>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public JogoDao(Context context){
        this.context = context;
    }
    @Override
    public JogoDao open() throws SQLException {
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
    public void insert(Jogo jogo) throws SQLException {
        db.insert("produto", null, getContentValues(jogo, true));
        db.insert("jogo", null, getContentValues(jogo, false));
    }

    @Override
    public void update(Jogo jogo) throws SQLException {
        db.update("produto", getContentValues(jogo, true), "codigo = " + jogo.getCodigo(), null);
        db.update("jogo", getContentValues(jogo, false), "codigo_produto = " + jogo.getCodigo(), null);
    }

    @Override
    public void delete(Jogo jogo) throws SQLException {
        db.delete("jogo", "codigo_produto = " + jogo.getCodigo(), null);
        db.delete("produto", "codigo = " + jogo.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Jogo findOne(Jogo jogo) throws SQLException {
        String sql = "SELECT produto.codigo, produto.nome, produto.preco, jogo.desenvolvedora, jogo.idade_recomendada "
                + "FROM produto, jogo WHERE produto.codigo = jogo.codigo_produto "
                + "AND produto.codigo = " + jogo.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            jogo.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            jogo.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            jogo.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
            jogo.setDesenvolvedora(cursor.getString(cursor.getColumnIndex("desenvolvedora")));
            jogo.setIdadeRecomendada(cursor.getInt(cursor.getColumnIndex("idade_recomendada")));
        }
        cursor.close();
        return jogo;
    }

    @SuppressLint("Range")
    @Override
    public List<Jogo> findAll() throws SQLException {
        List<Jogo> jogos = new ArrayList<>();
        String sql = "SELECT produto.codigo, produto.nome, produto.preco, jogo.desenvolvedora, jogo.idade_recomendada "
                + "FROM produto, jogo WHERE produto.codigo = jogo.codigo_produto ";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Jogo j = new Jogo();
            j.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            j.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            j.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
            j.setDesenvolvedora(cursor.getString(cursor.getColumnIndex("desenvolvedora")));
            j.setIdadeRecomendada(cursor.getInt(cursor.getColumnIndex("idade_recomendada")));
            jogos.add(j);
            cursor.moveToNext();
        }
        cursor.close();
        return jogos;
    }

    private static ContentValues getContentValues(Jogo j, boolean flagSuper){
        ContentValues contentValues = new ContentValues();
        if(flagSuper){
            contentValues.put("codigo", j.getCodigo());
            contentValues.put("nome", j.getNome());
            contentValues.put("preco", j.getPreco());
        } else {
            contentValues.put("desenvolvedora", j.getDesenvolvedora());
            contentValues.put("idade_recomendada", j.getIdadeRecomendada());
            contentValues.put("codigo_produto", j.getCodigo());
        }
        return contentValues;
    }
}
