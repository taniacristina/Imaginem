package com.example.imaginem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Questoes extends AppCompatActivity {

    public static final int IMAGEM_INTERNA = 1;
    public static final int PERMISSAO_REQUEST = 2;

    ImageView imgQuestao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);
        imgQuestao = (ImageView)findViewById(R.id.imageView);
    }

    public void pegarImagem(View view) {
        // Chama todas as aplicações
        Intent intentImagem = new Intent(Intent.ACTION_GET_CONTENT);
        intentImagem.setType("image/*");
        startActivityForResult(intentImagem, IMAGEM_INTERNA);
    }

    // Checando resposta
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        //Se houver aplicações com resposta
        if (requestCode == IMAGEM_INTERNA){
            //Se o processamento foi OK
            if (resultCode == RESULT_OK){
                Uri imagemSelecionada = intent.getData(); //Acessa recurso de imagem da aplicação

                String[] colunas = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imagemSelecionada, colunas, null, null, null);
                cursor.moveToFirst();

                int indexColuna = cursor.getColumnIndex(colunas[0]);
                String pathImg = cursor.getString(indexColuna); //caminho da imagem
                cursor.close();

                Bitmap bitmap = BitmapFactory.decodeFile(pathImg);
                imgQuestao.setImageBitmap(bitmap);
            }
        }
    }


}
