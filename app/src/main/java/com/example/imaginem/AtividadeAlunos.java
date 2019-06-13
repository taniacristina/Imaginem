package com.example.imaginem;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class AtividadeAlunos extends AppCompatActivity {

    String titulo;
    String descricao;
    String idAtv;
    String idQuestao;
    String idImagem;
    String imagem;
    String resultado;
    String palavra1;
    String palavra2;
    String palavra3;
    String erro1;
    String erro2;
    long result;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_alunos);

        final BancoController banco = new BancoController(getBaseContext());
        final CriaBanco db = new CriaBanco(getBaseContext());
        Bundle extras = getIntent().getExtras();

        titulo = extras.getString("titulo");
        descricao = extras.getString("descricao");
        idAtv = extras.getString("idAtv");

        resultado = db.idQuestao(idAtv);
        if(resultado.equals("erro")){
            Toast.makeText(getApplicationContext(),"Erro ao consultar",Toast.LENGTH_LONG).show();
        } else {
            idQuestao = resultado;
        }

        resultado = db.idImagem(idQuestao);
        if(resultado.equals("erro")){
            Toast.makeText(getApplicationContext(),"Erro ao consultar",Toast.LENGTH_LONG).show();
        } else {
            idImagem = resultado;
        }

        resultado = db.imagem(idImagem);
        if(resultado.equals("erro")){
            Toast.makeText(getApplicationContext(),"Erro ao consultar",Toast.LENGTH_LONG).show();
        } else {
            imagem = resultado;
        }

        TextView textView = findViewById(R.id.textView2);
        final EditText editText = findViewById(R.id.editText11);
        ImageView imageView = findViewById(R.id.imageView8);

        textView.setText(titulo);
        File imgFile = new  File(imagem);

        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }


        Button button = (Button)findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextString = editText.getText().toString();

                // Consulta palavras
                Cursor cursor = db.buscaPalavras(idQuestao);
                if(cursor.getCount() > 0) {

                    if(cursor.moveToFirst()) {

                        palavra1 = cursor.getString(cursor.getColumnIndexOrThrow("palavra1"));
                        palavra2 = cursor.getString(cursor.getColumnIndexOrThrow("palavra2"));
                        palavra3 = cursor.getString(cursor.getColumnIndexOrThrow("palavra3"));

                        if(editTextString.equals(palavra1) || editText.equals(palavra2) || editText.equals(palavra3)) {
                            Toast.makeText(getApplicationContext(),"Acertou, vamos para a próxima!",Toast.LENGTH_LONG).show();
                        } else {
                            if(i == 0) {
                                erro1 = editTextString;
                                i++;
                                Toast.makeText(getApplicationContext(),"Errado",Toast.LENGTH_LONG).show();
                            } else {
                                if(i == 1) {
                                    erro2 = editTextString;
                                    result = banco.insereErros(erro1,erro2,idImagem,idQuestao);
                                    if(result != -1) {
                                        Toast.makeText(getApplicationContext(),"Errado :(, vamos para a próxima!",Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Erro ao inserir registro",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }


                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Erro ao consultar",Toast.LENGTH_LONG).show();
                }
            }
        });

  
    }
}
