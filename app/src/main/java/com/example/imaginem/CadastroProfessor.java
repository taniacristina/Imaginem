package com.example.imaginem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imaginem.BancoController;

public class CadastroProfessor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        Button botao = (Button)findViewById(R.id.button6);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.editText14);
                EditText area = (EditText)findViewById(R.id.editText15);
                EditText usuario = (EditText)findViewById(R.id.editText16);
                EditText senha = (EditText)findViewById((R.id.editText17));

                String nomeString = nome.getText().toString();
                String areaString = area.getText().toString();
                String usuarioString = usuario.getText().toString();
                String senhaString = senha.getText().toString();
                String resultado;

                resultado = crud.insereDado(nomeString,areaString,usuarioString,senhaString);

                Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
            }
        });
    }
}
