package com.example.appquiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        sql += "REPOSTA text,";
        sql += "CORRETA integer check (CORRETA = 1 or CORRETA = 0) default 0,";
        sql += "constraint FK_RESPOSTA_ID_PERGUNTA foreign key (ID_PERGUNTA) references PERGUNTA(ID));";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void testaPerguntas(Context context) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select MAX(ID) from PERGUNTA";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) > 10) {
                criaPerguntas(context);
            }
        }
    }

    private int ultimaPergunta() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select max(ID) from PERGUNTA";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    private int insertPergunta(String pergunta) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "insert into PERGUNTA () values ('" + pergunta + "');";
        db.execSQL(sql);

        sql = "select max(ID) from PERGUNTA;";
        Cursor cursor = db.rawQuery(sql, null);
        if (!cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    private void insertResposta(int pergunta, String resposta, int correta) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "insert into RESPOSTA (ID_PERGUNTA, RESPOSTA, CORRETA) values (" + pergunta + ", '" + resposta + "', " + correta + ");";
        db.execSQL(sql);
    }

    public void criaPerguntas(Context current) {
        try {
            AssetManager a = current.getResources().getAssets();
            InputStream inputStream = a.open("arq.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha;
            int controller = 0;
            int resposta = 0;
            int pergunta = 0;
            while ((linha = bufferedReader.readLine()) != null) {
                controller += 1;
                if (controller == 1) {
                    pergunta = insertPergunta(linha);
                    resposta = 1;
                } else if (controller <= 4) {
                    insertResposta(pergunta,linha,resposta);
                    resposta = 0;
                } else {
                    controller = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
