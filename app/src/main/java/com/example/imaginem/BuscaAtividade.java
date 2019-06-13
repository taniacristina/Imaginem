package com.example.imaginem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BuscaAtividade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_atividade);
    }

    public void buscaAtividade(View view) {
        String titulo = null;
        String descricao = null;
        Bundle bundle = new Bundle();

        EditText idAtv = (EditText)findViewById(R.id.editText12);
        String idAtvString = idAtv.getText().toString();
        CriaBanco db = new CriaBanco(getBaseContext());

        Cursor c = db.buscaAtividade(idAtvString);
        if(c.getCount() > 0) {
            if(c.moveToFirst()) {
                titulo = c.getString(c.getColumnIndexOrThrow("titulo"));
                descricao = c.getString(c.getColumnIndexOrThrow("descricao"));
                Intent intentAlunos = new Intent(BuscaAtividade.this, AtividadeAlunos.class);
                bundle.putString("titulo",titulo);
                bundle.putString("descricao",descricao);
                bundle.putString("idAtv",idAtvString);
                intentAlunos.putExtras(bundle);
                startActivity(intentAlunos);
            }
        } else {
            Toast.makeText(getApplicationContext(),"Erro ao consultar",Toast.LENGTH_LONG).show();
        }

    }
}
