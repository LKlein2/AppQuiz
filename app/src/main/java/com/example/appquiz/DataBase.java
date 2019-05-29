package com.example.appquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "db_quiz";

    public DataBase(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;

        // ************* TABELA USUARIO *************
        sql = "create table USUARIO (";
        sql += "ID integer primary key autoincrement,";
        sql += "NOME text,";
        sql += "FASE integer,";
        sql += "PONTUACAO integer);";

        // ************* TABELA PERGUNTA *************
        sql += "create table PERGUNTA (";
        sql += "ID integer primary key autoincrement,";
        sql += "PERGUNTA text not null);";

        // ************* TABELA RESPOSTA *************
        sql += "create table RESPOSTA (";
        sql += "ID integer primary key autoincrement,";
        sql += "ID_PERGUNTA integer,";
        sql += "CORRETA integer check (CORRETA = 1 or CORRETA = 0) default 0,";
        sql += "constraint FK_RESPOSTA_ID_PERGUNTA foreign key (ID_PERGUNTA) references PERGUNTA(ID));";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addUsuario () {
        SQLiteDatabase db = this.getWritableDatabase();
        Usuario usuario = Usuario.getINSTANCE();

        String sql = "select 1 from usuario";
        Cursor cursor = db.rawQuery(sql, null);
        if (!cursor.moveToFirst()) {
            sql = "insert into USUARIO (NOME, FASE, PONTUACAO) values ('";
            sql += usuario.getNome() + "', " + usuario.u_fase + ", " + usuario.u_pontuacao + ");";
            db.execSQL(sql);
        }
    }

    public void retUsuario() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select NOME, FASE, PONTUACAO from usuario";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            Usuario u = Usuario.getINSTANCE();
            u.setNome(cursor.getString(0));
            u.u_fase = cursor.getInt(1);
            u.u_pontuacao = cursor.getInt(2);

        }
    }
}
