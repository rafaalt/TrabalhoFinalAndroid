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
import android.widget.CheckBox;
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
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.AppCompat;

public class CadastroCategoria extends AppCompat {

    private ArrayList<String> categoriasSelecionadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        categoriasSelecionadas = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);
        Button btnSair = findViewById(R.id.btnCadastroCategoria);
        btnSair.setOnClickListener(view -> {
            checkBoxControl();
            if(categoriasSelecionadas.isEmpty()){
                Toast.makeText(CadastroCategoria.this, "Escolha ao menos uma categoria", Toast.LENGTH_SHORT).show();
            }
            else{
                Bundle resultBundle = new Bundle();
                Intent resultIntent = new Intent();
                resultBundle.putStringArrayList("categorias", categoriasSelecionadas);
                resultIntent.putExtras(resultBundle);

                this.setResult(CadastroActivity.CATEGORIA_CODE, resultIntent);
                this.finish();
            }
        });
    }


    public void checkBoxControl() {
        CheckBox cb2 = findViewById(R.id.checkBolo);
        CheckBox cb3 = findViewById(R.id.checkDoce);
        CheckBox cb4 = findViewById(R.id.checkCarne);
        CheckBox cb5 = findViewById(R.id.checkPeixe);
        CheckBox cb9 = findViewById(R.id.checkMassa);
        CheckBox cb10 = findViewById(R.id.checkSopa);
        CheckBox cb11 = findViewById(R.id.checkBebida);
        CheckBox cb13 = findViewById(R.id.checkOutros);

        if(cb2.isChecked()){
            categoriasSelecionadas.add(cb2.getText().toString());
        }
        if(cb3.isChecked()){
            categoriasSelecionadas.add(cb3.getText().toString());
        }
        if(cb4.isChecked()){
            categoriasSelecionadas.add(cb4.getText().toString());
        }
        if(cb5.isChecked()){
            categoriasSelecionadas.add(cb5.getText().toString());
        }
        if(cb9.isChecked()){
            categoriasSelecionadas.add(cb9.getText().toString());
        }
        if(cb10.isChecked()){
            categoriasSelecionadas.add(cb10.getText().toString());
        }
        if(cb11.isChecked()){
            categoriasSelecionadas.add(cb11.getText().toString());
        }
        if(cb13.isChecked()){
            categoriasSelecionadas.add(cb13.getText().toString());
        }

    }
}