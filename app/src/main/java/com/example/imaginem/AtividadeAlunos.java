package com.example.imaginem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AtividadeAlunos extends AppCompatActivity {
    String titulo;
    String descricao;
    String idAtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_alunos);

        Bundle extras = getIntent().getExtras();

        titulo = extras.getString("titulo");
        descricao = extras.getString("descricao");
        idAtv = extras.getString("idAtv");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(titulo);
    }
}
