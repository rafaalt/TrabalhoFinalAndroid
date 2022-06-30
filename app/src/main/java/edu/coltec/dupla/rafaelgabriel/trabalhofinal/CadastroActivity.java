package edu.coltec.dupla.rafaelgabriel.trabalhofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText txtNome = findViewById(R.id.cadastroNomeReceita);
        EditText txtAutor = findViewById(R.id.cadastroAutor);
        Button btnAdicionar = findViewById(R.id.cadastroBtnAdicionar);
        Button btnLimpar = findViewById(R.id.cadastroBtnLimpar);
        Button btnCadastrar = findViewById(R.id.cadastroBtnCadastrar);
        EditText txtModoPreparo = findViewById(R.id.cadastroModoPreparo);
    }
}