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

public class ClienteDao implements IClienteDao, ICRUDDao<Cliente>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase db;

    public ClienteDao(Context context) {
        this.context = context;
    }

    @Override
    public ClienteDao open() throws SQLException {
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
    public void insert(Cliente cliente) throws SQLException {
        db.insert("cliente", null, getContentValues(cliente));
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        db.update("cliente", getContentValues(cliente), "cpf = " + cliente.getCpf(), null);
    }

    @Override
    public void delete(Cliente cliente) throws SQLException {
        db.delete("cliente", "cpf = " + cliente.getCpf(), null);
    }

    @SuppressLint("Range")
    @Override
    public Cliente findOne(Cliente cliente) throws SQLException {
        String sql = "SELECT cpf, nome, email FROM cliente WHERE cpf = " + cliente.getCpf();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            cliente.setCpf(cursor.getInt(cursor.getColumnIndex("cpf")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        }
        cursor.close();
        return cliente;
    }

    @SuppressLint("Range")
    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT cpf, nome, email FROM cliente";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Cliente c = new Cliente();
            c.setCpf(cursor.getInt(cursor.getColumnIndex("cpf")));
            c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            c.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            clientes.add(c);
            cursor.moveToNext();
        }
        cursor.close();
        return clientes;
    }

    private static ContentValues getContentValues(Cliente c){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cpf", c.getCpf());
        contentValues.put("nome", c.getNome());
        contentValues.put("email", c.getEmail());
        return contentValues;
    }

}
