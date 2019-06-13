package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ListaAtividades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades);

        Bundle extras = getIntent().getExtras();
        String idProfessor;

        // Recuperando o ID do professor
        if(extras != null) {
            idProfessor = extras.getString("idProfessor");
            //Toast.makeText(ListaAtividades.this, idProfessor,Toast.LENGTH_SHORT).show();
        }

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, CadastroAtividade.class);
        Bundle extras = getIntent().getExtras();
        Bundle bundle = new Bundle();

        if(extras != null) {
            String idProfessor = extras.getString("idProfessor");
            bundle.putString("idProfessor",idProfessor);
            intent.putExtras(bundle);
            startActivity(intent);
            //Toast.makeText(ListaAtividades.this, idProfessor,Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }
}
