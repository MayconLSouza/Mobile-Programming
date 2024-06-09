package br.edu.fateczl.biblioteca.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "BIBILIOTECA.DB";

    private static final int DATABASE_VER = 2;

    private static final String CREATE_TABLE_ALUNO =
            "CREATE TABLE aluno ( " +
                    "ra INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "nome varchar(100) NOT NULL, " +
                    "email varchar(50) NOT NULL);";

    private static final String CREATE_TABLE_EXEMPLAR =
            "CREATE TABLE exemplar ( " +
                    "codigo INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "nome varchar(50) NOT NULL, " +
                    "qtd_paginas INTEGER(10) NOT NULL);";

    private static final String CREATE_TABLE_LIVRO =
            "CREATE TABLE livro ( " +
                    "exemplar_codigo INTEGER(10), " +
                    "isbn char(13) NOT NULL, " +
                    "edicao INTEGER(10) NOT NULL, " +
                    "FOREIGN KEY (exemplar_codigo) REFERENCES exemplar (codigo) ON DELETE CASCADE);";

    private static final String CREATE_TABLE_REVISTA =
            "CREATE TABLE revista ( " +
                    "exemplar_codigo INTEGER(10), " +
                    "issn char(8) NOT NULL, " +
                    "FOREIGN KEY (exemplar_codigo) REFERENCES exemplar (codigo) ON DELETE CASCADE);";

    private static final String CREATE_TABLE_ALUGUEL =
            "CREATE TABLE aluguel ( " +
                    "exemplar_codigo INTEGER(10), " +
                    "aluno_ra INTEGER(10), " +
                    "data_retirada varchar(10) NOT NULL, " +
                    "data_devolucao varchar(10) NOT NULL, " +
                    "PRIMARY KEY (exemplar_codigo, aluno_ra, data_retirada), " +
                    "FOREIGN KEY (exemplar_codigo) REFERENCES exemplar (codigo) ON DELETE CASCADE, " +
                    "FOREIGN KEY (aluno_ra) REFERENCES aluno (ra) ON DELETE CASCADE);";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALUNO);
        db.execSQL(CREATE_TABLE_EXEMPLAR);
        db.execSQL(CREATE_TABLE_LIVRO);
        db.execSQL(CREATE_TABLE_REVISTA);
        db.execSQL(CREATE_TABLE_ALUGUEL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS aluguel");
            db.execSQL("DROP TABLE IF EXISTS livro");
            db.execSQL("DROP TABLE IF EXISTS revista");
            db.execSQL("DROP TABLE IF EXISTS exemplar");
            db.execSQL("DROP TABLE IF EXISTS aluno");

            onCreate(db);
        }
    }
}
