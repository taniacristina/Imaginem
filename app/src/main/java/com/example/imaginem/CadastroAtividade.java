package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAtividade extends AppCompatActivity {
    String idAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade);
    }

    // Chamando a função send do botão para fazer para fazer o cadastro da atividade
    public void sendMessage(View view) {

        BancoController banco = new BancoController(getBaseContext());
        String idProfessor = null;
        
        // Recebendo o id do professor
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            idProfessor = extras.getString("idProfessor");
            extras.putString("idProfessor",idProfessor);
        }

        // Recebe os valores digitados
        EditText titulo = (EditText)findViewById(R.id.editText5);
        EditText descricao = (EditText)findViewById(R.id.editText9);

        String tituloString = titulo.getText().toString();
        String descricaoString = descricao.getText().toString();

        long resultado;

        // Chamando a função de inserção da atividade
        resultado = banco.insereAtividade(tituloString,descricaoString,idProfessor);
        idAtividade = String.valueOf(resultado);

        if(resultado != -1) {
            Bundle bundle = new Bundle();
            bundle.putString("idProfessor",idProfessor);
            extras.putString("idAtividade",idAtividade);
            Toast.makeText(getApplicationContext(),"Registro Inserido com sucesso",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Questoes.class);
            intent.putExtras(extras);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Erro ao inserir registro",Toast.LENGTH_LONG).show();
        }

    }
}
