package com.example.imaginem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginProfessor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);
    }

    public void cadastroProfessor(View view) {
        Intent intentCadastroProf = new Intent(this, CadastroProfessor.class);
        startActivity(intentCadastroProf);
    }
}
