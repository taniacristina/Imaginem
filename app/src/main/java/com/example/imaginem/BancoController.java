package com.example.imaginem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.imaginem.CriaBanco;


public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereDado(String nome, String area, String usuario, String senha) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.AREA, area);
        valores.put(CriaBanco.USUARIO, usuario);
        valores.put(CriaBanco.SENHA,senha);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao inserir registro";
        } else {
            return "Registro Inserido com sucesso";
        }
    }

    public String insereAtividade(String titulo, String descricao, String id_pro) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.DESCRICAO, descricao);
        valores.put(CriaBanco.ID_PRO, id_pro);
        resultado = db.insert(CriaBanco.TABELA_atv, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao inserir registro";
        } else {
            return "Registro Inserido com sucesso";
        }
    }

    public long insereImagens(byte[] imagem, int idAtv) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.IMAGEM, imagem);
        resultado = db.insert(CriaBanco.TABELA_img, null, valores);
        db.close();
        return resultado;
    }

    public long inserePalavras(long idImg, String palavra1, String descricao1, String palavra2, String descricao2, String palavra3, String descricao3) {
        long resultado;
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.PALAVRA1, palavra1);
        valores.put(CriaBanco.PALAVRA2, palavra2);
        valores.put(CriaBanco.PALAVRA3, palavra3);
        valores.put(CriaBanco.DESCRICAO1, descricao1);
        valores.put(CriaBanco.DESCRICAO2, descricao2);
        valores.put(CriaBanco.DESCRICAO3, descricao3);
        valores.put(CriaBanco.ID_IMAGEM, idImg);
        resultado = db.insert(CriaBanco.TABELA_QUESTAO, null, valores);
        db.close();
        return resultado;
    }

}
