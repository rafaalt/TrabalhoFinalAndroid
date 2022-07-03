package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;

public class CadastroCategoria extends AppCompatActivity {

    private static int FOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDB appDB = new AppDB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        Button cadastroBtnFoto = findViewById(R.id.cadastroBtnFoto);

        cadastroBtnFoto.setOnClickListener(new View.OnClickListener() {
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
        ImageView imageView = findViewById(R.id.img_cadastro_categoria);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            Bitmap photoNew = null;
            try {
                photoNew = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(photoNew);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}