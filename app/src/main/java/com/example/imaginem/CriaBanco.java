package com.example.imaginem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "imaginem.db";
    public static final String TABELA = "professores";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String AREA = "area";
    public static final String USUARIO = "usuario";
    public static final String SENHA = "senha";

    public static final String TABELA_atv = "atividades";
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";

    public static final int VERSAO = 2;

    private final Context contexto;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + AREA + " text,"
                + USUARIO + " text,"
                + SENHA + " text"
                + ")";
        db.execSQL(sql);

        // Criando tabela de atividades
        String sql_atv = "CREATE TABLE "+TABELA_atv+"("
                + ID + " integer primary key autoincrement,"
                + TITULO + " text,"
                + DESCRICAO + " text"
                + ")";
        db.execSQL(sql_atv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_atv);
        onCreate(db);
    }

    // Método para validar o login
    public String validaLogin(String usuarioLogin, String senhaLogin) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM professores WHERE usuario=? AND senha=?",new String[]{usuarioLogin,senhaLogin});
        if(c.getCount() > 0) {
            return "OK";
        }
        return "Erro";
    }

    // Método para recuperar o ID do professor
    public String idProfessor(String usuario){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _id FROM professores WHERE usuario=?",new String[]{usuario});
        if(c.getCount() > 0) {
            if(c.moveToFirst()) {
                System.out.println("Valor usuario = "+c.getString(c.getColumnIndexOrThrow("_id")));
            }
            return c.toString();
        }
        System.out.println("Valor usuario"+c.getString(c.getColumnIndexOrThrow("_id")));
        return "Erro";
    }


}
