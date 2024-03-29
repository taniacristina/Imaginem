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

    public long insereAtividade(String titulo, String descricao, String id_pro) {
        ContentValues valores;
        long resultado;
        int idProfessor = Integer.parseInt(id_pro);

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.TITULO, titulo);
        valores.put(CriaBanco.DESCRICAO, descricao);
        valores.put(CriaBanco.ID_PRO, idProfessor);
        resultado = db.insert(CriaBanco.TABELA_atv, null, valores);
        db.close();

        return resultado;
    }

    public long insereImagens(String imagem) {
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

    public long insereRelacao(int idAtv, int idQuestao) {
        long resultado;
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.ID_ATV, idAtv);
        valores.put(CriaBanco.ID_QUESTAO, idQuestao);
        resultado = db.insert(CriaBanco.TABELA_RELACAO, null, valores);
        db.close();
        return resultado;
    }

    public long insereErros(String erro1, String erro2, String idImg, String IdQues) {
        long resultado;
        ContentValues valores;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.ERRO1, erro1);
        valores.put(CriaBanco.ERRO2, erro2);
        valores.put(CriaBanco.ID_IMAGEM, idImg);
        valores.put(CriaBanco.ID_QUESTAO, IdQues);
        resultado = db.insert(CriaBanco.TABELA_ERROS, null, valores);
        db.close();
        return resultado;
    }

}
