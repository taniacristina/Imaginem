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
    long resultado;
    long idImg;
    int idAtv;
    String imagePath;
    String idAtvString;
    String idProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);
        Bundle extras = getIntent().getExtras();

        // Recuperando o ID do professor
        if(extras != null) {
            idProfessor = extras.getString("idProfessor");
            idAtvString = extras.getString("idAtividade");
        }
        idAtv = Integer.parseInt(idAtvString);

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
        BancoController banco = new BancoController(getBaseContext());
        CriaBanco db = new CriaBanco(getBaseContext());

//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        imageBitmap = ((BitmapDrawable)imagem.getDrawable()).getBitmap();
//        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        imagemBytes = stream.toByteArray();
//
//        System.out.println("Imagem em bytes: "+imagemBytes);

        // Chamando a função de inserção da imagem
        resultado = banco.insereImagens(imagePath);
        idImg = resultado;
        if(resultado == -1) {
            Toast.makeText(getApplicationContext(),"Erro ao inserir registro",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"Registro Inserido com sucesso",Toast.LENGTH_LONG).show();
        }

    }

    public void inserirQuestao(View view) {
        BancoController banco = new BancoController(getBaseContext());
        long resultadoQuestao;
        // Recebe os valores digitados
        EditText palavra1 = (EditText)findViewById(R.id.editText);
        EditText descricao1 = (EditText)findViewById(R.id.editText2);
        EditText palavra2 = (EditText)findViewById(R.id.editText3);
        EditText descricao2 = (EditText)findViewById(R.id.editText4);
        EditText palavra3 = (EditText)findViewById(R.id.editText6);
        EditText descricao3 = (EditText)findViewById(R.id.editText8);

        String palavra1String = palavra1.getText().toString();
        String descricao1String = descricao1.getText().toString();
        String palavra2String = palavra2.getText().toString();
        String descricao2String = descricao2.getText().toString();
        String palavra3String = palavra3.getText().toString();
        String descricao3String = descricao3.getText().toString();

        resultadoQuestao = banco.inserePalavras(idImg, palavra1String,descricao1String, palavra2String,descricao2String, palavra3String, descricao3String);
        int resultadoQuestaoInt = (int) resultadoQuestao;
        long resultadoRelacao = banco.insereRelacao(idAtv, resultadoQuestaoInt);

        if(resultadoQuestao == -1 || resultadoRelacao == -1) {
            Toast.makeText(getApplicationContext(),"Erro ao inserir registro",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"Registro Inserido com sucesso",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ListaAtividades.class);
            Bundle extras = getIntent().getExtras();
            Bundle bundle = new Bundle();

            if(extras != null) {
                bundle.putString("idProfessor",idProfessor);
                bundle.putString("idAtividade",idAtvString);
                intent.putExtras(bundle);
                startActivity(intent);
                //Toast.makeText(Questoes.this, idProfessor,Toast.LENGTH_SHORT).show();
            }
            startActivity(intent);
        }

    }

    public void proximaQuestao(View view) {
        BancoController banco = new BancoController(getBaseContext());
        long resultadoQuestao;
        // Recebe os valores digitados
        EditText palavra1 = (EditText)findViewById(R.id.editText);
        EditText descricao1 = (EditText)findViewById(R.id.editText2);
        EditText palavra2 = (EditText)findViewById(R.id.editText3);
        EditText descricao2 = (EditText)findViewById(R.id.editText4);
        EditText palavra3 = (EditText)findViewById(R.id.editText6);
        EditText descricao3 = (EditText)findViewById(R.id.editText8);

        String palavra1String = palavra1.getText().toString();
        String descricao1String = descricao1.getText().toString();
        String palavra2String = palavra2.getText().toString();
        String descricao2String = descricao2.getText().toString();
        String palavra3String = palavra3.getText().toString();
        String descricao3String = descricao3.getText().toString();

        resultadoQuestao = banco.inserePalavras(resultado, palavra1String,descricao1String, palavra2String,descricao2String, palavra3String, descricao3String);
        int resultadoQuestaoInt = (int) resultadoQuestao;
        long resultadoRelacao = banco.insereRelacao(idAtv, resultadoQuestaoInt);

        if(resultadoQuestao == -1 || resultadoRelacao == -1) {
            Toast.makeText(getApplicationContext(),"Erro ao inserir registro",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"Registro Inserido com sucesso",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Questoes.class);
            Bundle extras = getIntent().getExtras();
            Bundle bundle = new Bundle();

            if(extras != null) {
                bundle.putString("idProfessor",idProfessor);
                bundle.putString("idAtividade",idAtvString);
                intent.putExtras(bundle);
                startActivity(intent);
                //Toast.makeText(Questoes.this, idProfessor,Toast.LENGTH_SHORT).show();
            }
            startActivity(intent);
        }

    }

    public void voltar(View view) {
        Intent intent= new Intent(Questoes.this, ListaAtividades.class);
        Bundle extras = getIntent().getExtras();
        Bundle bundle = new Bundle();

        if(extras != null) {
            bundle.putString("idProfessor",idProfessor);
            intent.putExtras(bundle);
            startActivity(intent);
            //Toast.makeText(Questoes.this, idProfessor,Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
    }

    public void apagar(View view) {
        EditText editText = findViewById(R.id.editText);
        EditText editText2 = findViewById(R.id.editText2);
        EditText editText3 = findViewById(R.id.editText3);
        EditText editText4 = findViewById(R.id.editText4);
        EditText editText6 = findViewById(R.id.editText6);
        EditText editText8 = findViewById(R.id.editText8);
        ImageView imageView = findViewById(R.id.imageView);

        editText.setText(null);
        editText2.setText(null);
        editText3.setText(null);
        editText4.setText(null);
        editText6.setText(null);
        editText8.setText(null);
//        imageView.setImageResource(0);
        recreate();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == TIRAR_FOTO && resultCode == RESULT_OK) {
            Uri selectedImage = intent.getData();
            imagePath = getImagePath(selectedImage);
            //System.out.println("Caminho imagem = "+imagePath);
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

    public String getImagePath(Uri contentUri) {
        String[] campos = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, campos, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }


}
