package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListaAtividades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, CadastroAtividade.class);
        startActivity(intent);
    }
}
