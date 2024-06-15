package br.edu.fateczl.locadoravideogame.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.locadoravideogame.model.Aluguel;
import br.edu.fateczl.locadoravideogame.model.Cliente;
import br.edu.fateczl.locadoravideogame.model.Console;
import br.edu.fateczl.locadoravideogame.model.Jogo;

public class AluguelDao implements IAluguelDao, ICRUDDao<Aluguel> {

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
        db.insert("aluguel", null, getContentValues(aluguel));
    }

    @Override
    public void update(Aluguel aluguel) throws SQLException {
        String whereQuery = "data_retirada = ? AND cpf_cliente = ? AND codigo_produto = ?";
        String[] whereArgs = new String[]{aluguel.getDataRetirada().toString(), String.valueOf(aluguel.getCliente().getCpf()),
                                String.valueOf(aluguel.getProduto().getCodigo())};
        db.update("aluguel", getContentValues(aluguel), whereQuery, whereArgs);
    }

    @Override
    public void delete(Aluguel aluguel) throws SQLException {
        String whereQuery = "data_retirada = ? AND cpf_cliente = ? AND codigo_produto = ?";
        String[] whereArgs = new String[]{aluguel.getDataRetirada().toString(), String.valueOf(aluguel.getCliente().getCpf()),
                String.valueOf(aluguel.getProduto().getCodigo())};
        db.delete("aluguel", whereQuery, whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public Aluguel findOne(Aluguel aluguel) throws SQLException {
        String sql = "SELECT aluguel.data_retirada AS data_retirada, aluguel.data_devolucao AS data_devolucao, " +
                "cliente.cpf AS cpf, cliente.nome AS nome_cliente, cliente.email AS email, " +
                "produto.codigo AS codigo_produto, produto.nome AS nome_produto, produto.preco AS preco, " +
                "jogo.desenvolvedora AS desenvolvedora, jogo.idade_recomendada AS idade_recomendada, " +
                "console.fabricante AS fabricante " +
                "FROM aluguel " +
                "INNER JOIN cliente ON aluguel.cpf_cliente = cliente.cpf " +
                "INNER JOIN produto ON aluguel.codigo_produto = produto.codigo " +
                "LEFT JOIN jogo ON produto.codigo = jogo.codigo_produto " +
                "LEFT JOIN console ON produto.codigo = console.codigo_produto " +
                "WHERE aluguel.data_retirada = ? " +
                "AND aluguel.cpf_cliente = ? " +
                "AND aluguel.codigo_produto = ?";

        String[] whereArgs = new String[]{
                aluguel.getDataRetirada().toString(),
                String.valueOf(aluguel.getCliente().getCpf()),
                String.valueOf(aluguel.getProduto().getCodigo())
        };
        Cursor cursor = db.rawQuery(sql, whereArgs);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Cliente cliente = new Cliente();
            cliente.setCpf(cursor.getInt(cursor.getColumnIndex("cpf")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluguel.setCliente(cliente);

            if(!cursor.isNull(cursor.getColumnIndex("fabricante"))){
                Console console = new Console();
                console.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo_produto")));
                console.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
                console.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
                console.setFabricante(cursor.getString(cursor.getColumnIndex("fabricante")));
                aluguel.setProduto(console);
            } else {
                Jogo jogo = new Jogo();
                jogo.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo_produto")));
                jogo.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
                jogo.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
                jogo.setDesenvolvedora(cursor.getString(cursor.getColumnIndex("desenvolvedora")));
                jogo.setIdadeRecomendada(cursor.getInt(cursor.getColumnIndex("idade_recomendada")));
                aluguel.setProduto(jogo);
            }

            aluguel.setDataRetirada(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_retirada"))));
            aluguel.setDataDevolucao(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_devolucao"))));
        }
        cursor.close();
        return aluguel;
    }

    @SuppressLint("Range")
    @Override
    public List<Aluguel> findAll() throws SQLException {
        List<Aluguel> alugueis = new ArrayList<>();
        String sql = "SELECT aluguel.data_retirada AS data_retirada, aluguel.data_devolucao AS data_devolucao, cliente.cpf AS cpf, cliente.nome AS nome_cliente, cliente.email AS email, "
                + "produto.codigo AS codigo_produto, produto.nome AS nome_produto, produto.preco AS preco, jogo.desenvolvedora AS desenvolvedora, jogo.idade_recomendada AS idade_recomendada, "
                + "console.fabricante AS fabricante FROM aluguel INNER JOIN cliente ON aluguel.cpf_cliente = cliente.cpf INNER JOIN produto ON aluguel.codigo_produto = produto.codigo "
                + "LEFT JOIN jogo ON produto.codigo = jogo.codigo_produto LEFT JOIN console ON produto.codigo = console.codigo_produto ";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            Aluguel aluguel = new Aluguel();
            Cliente cliente = new Cliente();
            cliente.setCpf(cursor.getInt(cursor.getColumnIndex("cpf")));
            cliente.setNome(cursor.getString(cursor.getColumnIndex("nome_cliente")));
            cliente.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluguel.setCliente(cliente);
            if(!cursor.isNull(cursor.getColumnIndex("fabricante"))){
                Console console = new Console();
                console.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo_produto")));
                console.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
                console.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
                console.setFabricante(cursor.getString(cursor.getColumnIndex("fabricante")));
                aluguel.setProduto(console);
            } else {
                Jogo jogo = new Jogo();
                jogo.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo_produto")));
                jogo.setNome(cursor.getString(cursor.getColumnIndex("nome_produto")));
                jogo.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
                jogo.setDesenvolvedora(cursor.getString(cursor.getColumnIndex("desenvolvedora")));
                jogo.setIdadeRecomendada(cursor.getInt(cursor.getColumnIndex("idade_recomendada")));
                aluguel.setProduto(jogo);
            }

            aluguel.setDataRetirada(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_retirada"))));
            aluguel.setDataDevolucao(LocalDate.parse(cursor.getString(cursor.getColumnIndex("data_devolucao"))));
            alugueis.add(aluguel);
            cursor.moveToNext();
        }
        cursor.close();
        return alugueis;
    }

    private static ContentValues getContentValues(Aluguel aluguel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("data_retirada", aluguel.getDataRetirada().toString());
        contentValues.put("data_devolucao", aluguel.getDataDevolucao().toString());
        contentValues.put("cpf_cliente", aluguel.getCliente().getCpf());
        contentValues.put("codigo_produto", aluguel.getProduto().getCodigo());
        return contentValues;
    }

}
