package com.example.imaginem;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginProfessor extends AppCompatActivity {

    CriaBanco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);

        Button btn_Entrar = (Button) findViewById(R.id.button5);
        final EditText usuario, senha;

        banco = new CriaBanco(this);

        usuario = (EditText)findViewById(R.id.editText7);
        senha = (EditText)findViewById(R.id.editText10);

        btn_Entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String usuarioString = usuario.getText().toString();
                String senhaString = senha.getText().toString();

                if(usuarioString.equals("")) {
                    Toast.makeText(LoginProfessor.this, "Usuario não inserido",Toast.LENGTH_SHORT).show();
                } else if(senhaString.equals("")){
                    Toast.makeText(LoginProfessor.this, "Senha não inserida",Toast.LENGTH_SHORT).show();
                } else {
                    String res = banco.validaLogin(usuarioString, senhaString);
                    if(res.equals("OK")) {
                        Toast.makeText(LoginProfessor.this, "login efetuado com sucesso",Toast.LENGTH_SHORT).show();
                        String idProfessor = banco.idProfessor(usuarioString);
                        Intent intentAtividades = new Intent(LoginProfessor.this, ListaAtividades.class);
                        startActivity(intentAtividades);
                    } else {
                        Toast.makeText(LoginProfessor.this, "login não efetuado",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public void cadastroProfessor(View view) {
        Intent intentCadastroProf = new Intent(this, CadastroProfessor.class);
        startActivity(intentCadastroProf);
    }

}
