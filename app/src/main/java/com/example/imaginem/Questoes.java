package com.example.imaginem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Questoes extends AppCompatActivity {

    private ImageView imagem;
    private Bitmap imageBitmap;
    private final int TIRAR_FOTO = 1;
    byte imagemBytes[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);
        Bundle extras = getIntent().getExtras();
        String idProfessor;

        // Recuperando o ID do professor
        if(extras != null) {
            idProfessor = extras.getString("idProfessor");
        }

        // Recuperando o imageview e acionando a ação de galeria
        imagem = findViewById(R.id.imageView);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, TIRAR_FOTO);
            }
        });
    }

    public void inserirImagem(View view) {
        String resultado;
        BancoController banco = new BancoController(getBaseContext());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap = ((BitmapDrawable)imagem.getDrawable()).getBitmap();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imagemBytes = stream.toByteArray();

        // Chamando a função de inserção da imagem
        resultado = banco.insereImagens(imagemBytes);
        if(resultado.equals("Registro Inserido com sucesso")) {
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
        }

    }

    public void inserirQuestao(View view) {
        CriaBanco banco = new CriaBanco(this);
        //int idImg = banco.idImagem();
    }

    public void proximaQuestao(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            Uri selectedImage = intent.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imagem.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }


}
