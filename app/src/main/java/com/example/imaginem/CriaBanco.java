package com.example.imaginem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CriaBanco extends SQLiteOpenHelper {

    // Tabela professores
    public static final String NOME_BANCO = "imaginem.db";
    public static final String TABELA = "professores";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String AREA = "area";
    public static final String USUARIO = "usuario";
    public static final String SENHA = "senha";

    // Tabela atividades
    public static final String TABELA_atv = "atividades";
    public static final String TITULO = "titulo";
    public static final String DESCRICAO = "descricao";
    public static final String ID_PRO = "_idPro";

    // Tabela imagens
    public static final String TABELA_img = "imagem";
    public static final String ID_IMG = "_idImg";
    public static final String IMAGEM = "imagem";

    // Tabela questão
    public static final String TABELA_QUESTAO = "questao";
    public static final String ID_QUESTAO = "_idQues";
    public static final String PALAVRA1 = "palavra1";
    public static final String PALAVRA2 = "palavra2";
    public static final String PALAVRA3 = "palavra3";
    public static final String DESCRICAO1 = "descricao1";
    public static final String DESCRICAO2 = "descricao2";
    public static final String DESCRICAO3 = "descricao3";

    // Tabela de relação entre atividade e questão
    public static final String TABELA_RELACAO = "relacao";
    public static final String ID_REL = "_idRel";

    public static final int VERSAO = 2;

    private final Context contexto;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabela de professores
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
                + DESCRICAO + " text,"
                + ID_PRO + " integer"
                + ")";
        db.execSQL(sql_atv);

        // Criando tabela de Imagens
        String sql_img = "CREATE TABLE "+TABELA_img+"("
                + ID_IMG + " integer primary key autoincrement,"
                + IMAGEM + " blob"
                + ")";
        db.execSQL(sql_img);

        // Criando tabela de questões
        String sql_palavra = "CREATE TABLE "+TABELA_QUESTAO+"("
                + ID_QUESTAO + " integer primary key autoincrement,"
                + PALAVRA1 + " text,"
                + PALAVRA2 + " text,"
                + PALAVRA3 + " text,"
                + DESCRICAO1 + " text,"
                + DESCRICAO2 + " text,"
                + DESCRICAO3 + " text,"
                + ID_IMG + " integer"
                + ")";
        db.execSQL(sql_palavra);

        // Criando tabela de relação entre atividade e questão
        String sql_relacao = "CREATE TABLE "+TABELA_RELACAO+"("
                + ID_REL + " integer primary key autoincrement,"
                + ID_IMG + " integer,"
                + ID_QUESTAO + " integer"
                + ")";
        db.execSQL(sql_relacao);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_atv);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_img);
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
                return c.getString(c.getColumnIndexOrThrow("_id"));
            }
        }
        return "Erro";
    }


    // Método para recuperar o ID da imagem
    public String idImagem() {
        return "";
    }

}
