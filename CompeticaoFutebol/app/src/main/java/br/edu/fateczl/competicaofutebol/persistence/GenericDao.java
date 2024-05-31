package br.edu.fateczl.competicaofutebol.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "ESPORTE.DB";
    private static final int DATABASE_VER = 2;
    private static final String CREATE_TABLE_TIME =
            "CREATE TABLE time ( " +
                    "codigo INT NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "cidade VARCHAR(80) NOT NULL);";
    private static final String CREATE_TABLE_JOGADOR =
            "CREATE TABLE jogador ( " +
                    "id INT NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "data_nasc VARCHAR(10) NOT NULL, " +
                    "altura DECIMAL(4, 2) NOT NULL, " +
                    "peso DECIMAL(4, 1) NOT NULL, " +
                    "codigo_time INT NOT NULL, " +
                    "FOREIGN KEY (codigo_time) REFERENCES time(codigo));";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TIME);
        db.execSQL(CREATE_TABLE_JOGADOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS jogador");
            db.execSQL("DROP TABLE IF EXISTS time");
            onCreate(db);
        }
    }
}
