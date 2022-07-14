package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.ReceitaDAO;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.AppCompat;

public class ReceitaDetailActivity extends AppCompat {

    private static int FOTO_CODE = 1;
    private Receita receitaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDB appDB = new AppDB(this);
        ReceitaBLL receitaBLL = new ReceitaBLL(appDB);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_detail);

        ImageView imageView = findViewById(R.id.img_receita_detail);
        TextView txt_detail_nome = findViewById(R.id.txt_detail_nome);
        TextView txt_detail_autor = findViewById(R.id.txt_detail_autor);
        TextView txt_detail_preparo = findViewById(R.id.txt_detail_preparo);
        TextView txt_detail_ingredientes = findViewById(R.id.txt_detail_ingredientes);
        RatingBar rtn_detail_dificuldades = findViewById(R.id.rtn_detail_dificuldade);
        Button btnRemover = findViewById(R.id.detailBtnRemover);
        Button bntNovaFoto = findViewById(R.id.detailBtnNovaFoto);
        Intent intent = getIntent();
        Receita receita = (Receita) intent.getSerializableExtra("receita");
        receitaAtual = receita;
        txt_detail_nome.setText(receita.getNome());
        txt_detail_autor.setText(receita.getAutor());
        String novoIngrediente = "";
        for(String x : receita.getIngredientes())
            novoIngrediente += " -" + x + "\n";
        txt_detail_ingredientes.setText(novoIngrediente);
        txt_detail_preparo.setText(receita.getModoDePreparo());
        rtn_detail_dificuldades.setRating(receita.getDificuldade());
        ProgressBar progressBar = findViewById(R.id.barraProgresso);
        progressBar.setVisibility(View.VISIBLE);
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String filename = receitaAtual.getFotoDaReceita();
                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                    File file = new File(directory, filename);
                    if(file.exists())
                        imageView.setImageDrawable(Drawable.createFromPath(file.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    ProgressBar progressBar = findViewById(R.id.barraProgresso);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        };
        thread.start();

        btnRemover.setOnClickListener(view -> {
            if(receitaBLL.delete(receita))
                this.finish();
        });

        bntNovaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,FOTO_CODE);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.img_receita_detail);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            Bitmap photoNew = null;
            try {
                photoNew = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(photoNew);

                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                Toast.makeText(this, receitaAtual.getFotoDaReceita(), Toast.LENGTH_SHORT).show();
                File file = new File(directory, receitaAtual.getFotoDaReceita());
                    Log.d("path", file.toString());
                    FileOutputStream fos = null;
                    try{
                        fos = new FileOutputStream(file);
                        photoNew.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        //Toast.makeText(ReceitaDetailActivity.this, "Imagem salva com sucesso", Toast.LENGTH_SHORT).show();
                        fos.flush();
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                //Converter para dados
                byte[] fotoEmBytes;
                ByteArrayOutputStream stremDaFotoEmBytes = new ByteArrayOutputStream();
                photoNew.compress(Bitmap.CompressFormat.PNG, 70, stremDaFotoEmBytes);
                fotoEmBytes = stremDaFotoEmBytes.toByteArray();

                //Converter para String
                String fotoEmString = Base64.encodeToString(fotoEmBytes, Base64.DEFAULT);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}