package br.edu.fateczl.locadoravideogame.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Console;

public class ConsoleDao implements IConsoleDao, ICRUDDao<Console>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public ConsoleDao(Context context){
        this.context = context;
    }

    @Override
    public ConsoleDao open() throws SQLException {
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
    public void insert(Console console) throws SQLException {
        db.insert("produto", null, getContentValues(console, true));
        db.insert("console", null, getContentValues(console, false));
    }

    @Override
    public void update(Console console) throws SQLException {
        db.update("produto", getContentValues(console, true), "codigo = " + console.getCodigo(), null);
        db.update("console", getContentValues(console, false),"codigo_produto = " + console.getCodigo(), null);
    }

    @Override
    public void delete(Console console) throws SQLException {
        db.delete("produto", "codigo = " + console.getCodigo(), null);
        db.delete("console", "codigo_produto = " + console.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Console findOne(Console console) throws SQLException {
        String sql = "SELECT produto.codigo, produto.nome, produto.preco, console.fabricante "
                + "FROM produto, console WHERE produto.codigo = console.codigo_produto "
                + "AND produto.codigo = " + console.getCodigo();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            console.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            console.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            console.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
            console.setFabricante(cursor.getString(cursor.getColumnIndex("fabricante")));
        }
        cursor.close();
        return console;
    }

    @SuppressLint("Range")
    @Override
    public List<Console> findAll() throws SQLException {
        List<Console> consoles = new ArrayList<>();
        String sql = "SELECT produto.codigo, produto.nome, produto.preco, console.fabricante "
                + "FROM produto, console WHERE produto.codigo = console.codigo_produto ";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Console console = new Console();
            console.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            console.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            console.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
            console.setFabricante(cursor.getString(cursor.getColumnIndex("fabricante")));
            consoles.add(console);
            cursor.moveToNext();
        }
        cursor.close();
        return consoles;
    }

    private static ContentValues getContentValues(Console c, boolean flagSuper){
        ContentValues contentValues = new ContentValues();
        if(flagSuper){
            contentValues.put("codigo", c.getCodigo());
            contentValues.put("nome", c.getNome());
            contentValues.put("preco", c.getPreco());
        } else {
            contentValues.put("fabricante", c.getFabricante());
            contentValues.put("codigo_produto", c.getCodigo());
        }
        return contentValues;
    }
}
