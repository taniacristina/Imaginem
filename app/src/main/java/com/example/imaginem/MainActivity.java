package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.imaginem.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Chamando a atividade CadastroAtividade
    public void sendMessage(View view) {
        Intent intent = new Intent(this, CadastroAtividade.class);
        startActivity(intent);
    }

    // Chamando a atividade ListaAtividades
    public void buscaAtividade(View view) {
        Intent intentLista = new Intent(this, BuscaAtividade.class);
        startActivity(intentLista);
    }

    // Chamando a atividade DadosAtividades
    public void loginProfessor (View view) {
        Intent intentDados = new Intent(this, LoginProfessor.class);
        startActivity(intentDados);
    }
}
