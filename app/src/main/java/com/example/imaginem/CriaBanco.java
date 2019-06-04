package com.example.imaginem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "professores";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String AREA = "area";
    public static final String USUARIO = "usuario";
    public static final String SENHA = "senha";
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    public String validaLogin(String usuarioLogin, String senhaLogin) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM professores WHERE usuario=? AND senha=?",new String[]{usuarioLogin,senhaLogin});
        if(c.getCount() > 0) {
            return "OK";
        }
        return "Erro";
    }


}
