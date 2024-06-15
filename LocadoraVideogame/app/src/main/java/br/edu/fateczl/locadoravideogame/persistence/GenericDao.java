package br.edu.fateczl.locadoravideogame.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "LOCADORA.DB";
    private static final int DATABASE_VER = 1;

    private static final String CREATE_TABLE_CLIENTE =
             "CREATE TABLE cliente( " +
                    "cpf INTEGER(11) NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL);";
    private static final String CREATE_TABLE_PRODUTO =
            "CREATE TABLE produto( " +
                    "codigo INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "preco DECIMAL(10,2) NOT NULL);";
    private static final String CREATE_TABLE_CONSOLE =
            "CREATE TABLE console( " +
                    "fabricante VARCHAR(50) NOT NULL, " +
                    "codigo_produto INTEGER(10), " +
                    "FOREIGN KEY (codigo_produto) REFERENCES produto (codigo) ON DELETE CASCADE);";
    private static final String CREATE_TABLE_JOGO =
            "CREATE TABLE jogo( " +
                    "desenvolvedora VARCHAR(50) NOT NULL, " +
                    "idade_recomendada INTEGER(10) NOT NULL, " +
                    "codigo_produto INTEGER(10), " +
                    "FOREIGN KEY (codigo_produto) REFERENCES produto (codigo) ON DELETE CASCADE);";
    private static final String CREATE_TABLE_ALUGUEL =
            "CREATE TABLE aluguel( " +
                    "data_retirada VARCHAR(10) NOT NULL, " +
                    "data_devolucao VARCHAR(10) NOT NULL, " +
                    "cpf_cliente INTEGER(11), " +
                    "codigo_produto INTEGER(10), " +
                    "FOREIGN KEY (cpf_cliente) REFERENCES cliente (cpf) ON DELETE CASCADE, " +
                    "FOREIGN KEY (codigo_produto) REFERENCES produto (codigo) ON DELETE CASCADE, " +
                    "PRIMARY KEY (data_retirada, cpf_cliente, codigo_produto));";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTE);
        db.execSQL(CREATE_TABLE_PRODUTO);
        db.execSQL(CREATE_TABLE_CONSOLE);
        db.execSQL(CREATE_TABLE_JOGO);
        db.execSQL(CREATE_TABLE_ALUGUEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS aluguel");
            db.execSQL("DROP TABLE IF EXISTS jogo");
            db.execSQL("DROP TABLE IF EXISTS console");
            db.execSQL("DROP TABLE IF EXISTS produto");
            db.execSQL("DROP TABLE IF EXISTS cliente");

            onCreate(db);
        }
    }
}
