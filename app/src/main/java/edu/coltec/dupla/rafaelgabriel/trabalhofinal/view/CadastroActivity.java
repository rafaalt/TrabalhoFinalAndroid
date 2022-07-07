package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;

public class CadastroActivity extends AppCompatActivity {
    private static int FOTO_CODE = 1;
    public static int CATEGORIA_CODE = 2;

    private Bitmap bitmapPadrao;

    private ArrayList<String> categoriasSelecionadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bitmapPadrao = null;
        AppDB appDB = new AppDB(this);
        categoriasSelecionadas = new ArrayList<>();
        ArrayList<String> ingredientesAux = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button cadastroBtnFoto = findViewById(R.id.btnAlterarFoto);
        Button btnSelectCategorias = findViewById(R.id.btnSelecionarCategorias);
        cadastroBtnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,FOTO_CODE);

            }
        });
        btnSelectCategorias.setOnClickListener(view -> {
            Intent intent = new Intent(CadastroActivity.this, CadastroCategoria.class);
            startActivityForResult(intent, CATEGORIA_CODE);
        });


        EditText txtNome = findViewById(R.id.cadastroNomeReceita);
        EditText txtAutor = findViewById(R.id.cadastroAutor);
        EditText txtIngred = findViewById(R.id.cadastroIngredienteAtual);
        TextView txtIngredientes = findViewById(R.id.cadastroListaIngredientes);
        Button btnAdicionar = findViewById(R.id.cadastroBtnAdicionar);
        Button btnLimpar = findViewById(R.id.cadastroBtnLimpar);
        Button btnCadastrar = findViewById(R.id.cadastroBtnCadastrar);
        EditText txtModoPreparo = findViewById(R.id.cadastroModoPreparo);
        RadioGroup radioDificuldade = findViewById(R.id.cadastroRadioDificuldade);

        btnAdicionar.setOnClickListener(view -> {
            String string = txtIngred.getText().toString();
            String txtAntigo = txtIngredientes.getText().toString();
            txtIngred.setText("");
            ingredientesAux.add(string);
            txtIngredientes.setText(txtAntigo + string + ", ");
        });

        btnLimpar.setOnClickListener(view -> {
            ingredientesAux.clear();
            txtIngredientes.setText("\n");
        });

        btnCadastrar.setOnClickListener(view -> {
            String nome = txtNome.getText().toString();
            String autor = txtAutor.getText().toString();
            String modo = txtModoPreparo.getText().toString();
            int id = radioDificuldade.getCheckedRadioButtonId();

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File file = new File(directory, nome + ".jpeg");
            if(!file.exists()){
                Log.d("path", file.toString());
                FileOutputStream fos = null;
                try{
                    fos = new FileOutputStream(file);
                    bitmapPadrao.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    Toast.makeText(CadastroActivity.this, "Imagem salva com sucesso", Toast.LENGTH_SHORT).show();
                    fos.flush();
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            if(id == -1){
                Toast.makeText(CadastroActivity.this, "Preencha a dificuldade", Toast.LENGTH_SHORT).show();
            }
            else {
                RadioButton radioSelecionado = findViewById(id);
                int dificuldade = Integer.parseInt(radioSelecionado.getText().toString());

                ReceitaBLL receitaBll = new ReceitaBLL(appDB);
                boolean wasCreated = receitaBll.create(nome, autor, categoriasSelecionadas, ingredientesAux, modo, dificuldade, nome + ".jpeg");

                if (wasCreated) {
                    Toast.makeText(this, "Receita cadastrada!", Toast.LENGTH_SHORT).show();
                    this.finish();
                } else {
                    Toast.makeText(this, "Erro no cadastro da receita", Toast.LENGTH_SHORT).show();
                }
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
                bitmapPadrao = photoNew;
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(photoNew);
        }
        else if (requestCode == CATEGORIA_CODE) {
            Bundle bundle = data.getExtras();
            categoriasSelecionadas = bundle.getStringArrayList("categorias");
            String txtNovo = "";
            for(String x : categoriasSelecionadas)
                txtNovo += x + ", ";
            String novo = txtNovo.substring(0, txtNovo.length()-2);

            TextView txtCat = findViewById(R.id.cadastroTxtCategorias);
            txtCat.setText(novo);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}