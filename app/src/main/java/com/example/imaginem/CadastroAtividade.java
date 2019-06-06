package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAtividade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade);
    }

    // Chamando a função send do botão
    public void sendMessage(View view) {
        BancoController banco = new BancoController(getBaseContext());

        EditText titulo = (EditText)findViewById(R.id.editText5);
        EditText descricao = (EditText)findViewById(R.id.editText9);

        String tituloString = titulo.getText().toString();
        String descricaoString = descricao.getText().toString();

        String resultado;

        resultado = banco.insereAtividade(tituloString,descricaoString);

        if(resultado.equals("Registro Inserido com sucesso")) {
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Questoes.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
        }

    }
}
