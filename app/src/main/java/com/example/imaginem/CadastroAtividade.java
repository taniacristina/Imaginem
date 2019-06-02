package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastroAtividade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade);
    }

    // Chamando a função send do botão
    public void sendMessage(View view) {
        Intent intent = new Intent(this, Questoes.class);
        startActivity(intent);
    }
}
