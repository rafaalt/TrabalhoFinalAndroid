package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppDB appDB = new AppDB(this);

        ArrayList<String> ingredientesAux = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        EditText txtNome = findViewById(R.id.cadastroNomeReceita);
        EditText txtAutor = findViewById(R.id.cadastroAutor);
        EditText txtIngred = findViewById(R.id.cadastroIngredienteAtual);
        EditText txtCategoria = findViewById(R.id.cadastroCategoria);
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
            String categoria = txtCategoria.getText().toString();
            String modo = txtModoPreparo.getText().toString();
            int id = radioDificuldade.getCheckedRadioButtonId();
            if(id == -1){
                Toast.makeText(CadastroActivity.this, "Preencha a dificuldade", Toast.LENGTH_SHORT).show();
            }
            else {
                RadioButton radioSelecionado = findViewById(id);
                int dificuldade = Integer.parseInt(radioSelecionado.getText().toString());

                ReceitaBLL receitaBll = new ReceitaBLL(appDB);
                boolean wasCreated = receitaBll.create(nome, autor, categoria, ingredientesAux, modo, dificuldade);

                if (wasCreated) {
                    Toast.makeText(this, "Receita cadastrada!", Toast.LENGTH_SHORT).show();
                    this.finish();
                } else {
                    Toast.makeText(this, "Erro no cadastro da receita", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}