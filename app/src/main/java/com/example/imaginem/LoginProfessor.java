package com.example.imaginem;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginProfessor extends AppCompatActivity {

    Button btn_Entrar = (Button) findViewById(R.id.button5);
    EditText usuario, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);

        usuario = (EditText)findViewById(R.id.editText7);
        senha = (EditText)findViewById(R.id.editText10);
        
        btn_Entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void cadastroProfessor(View view) {
        Intent intentCadastroProf = new Intent(this, CadastroProfessor.class);
        startActivity(intentCadastroProf);
    }

}
