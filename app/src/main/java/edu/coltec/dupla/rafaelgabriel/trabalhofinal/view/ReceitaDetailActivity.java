package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;

public class ReceitaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppDB appDB = new AppDB(this);
        ReceitaBLL receitaBLL = new ReceitaBLL(appDB);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_detail);

        TextView txt_detail_nome = findViewById(R.id.txt_detail_nome);
        TextView txt_detail_categoria = findViewById(R.id.txt_detail_categoria);
        TextView txt_detail_autor = findViewById(R.id.txt_detail_autor);
        TextView txt_detail_preparo = findViewById(R.id.txt_detail_preparo);
        TextView txt_detail_ingredientes = findViewById(R.id.txt_detail_ingredientes);
        TextView txt_detail_categoria_id = findViewById(R.id.txt_detail_categoria_id);
        RatingBar rtn_detail_dificuldades = findViewById(R.id.rtn_detail_dificuldade);
        Button btnRemover = findViewById(R.id.detailBtnRemover);
        Intent intent = getIntent();
        Receita receita = (Receita) intent.getSerializableExtra("receita");

        txt_detail_nome.setText(receita.getNome());
        txt_detail_categoria.setText(receita.getCategoria().getName());
        txt_detail_autor.setText(receita.getAutor());
        txt_detail_ingredientes.setText(receita.getIngredientes().toString());
        txt_detail_preparo.setText(receita.getModoDePreparo());
        rtn_detail_dificuldades.setRating(receita.getDificuldade());
        txt_detail_categoria_id.setText(receita.getCategoria().getId() + "");


        btnRemover.setOnClickListener(view -> {
            if(receitaBLL.delete(receita))
                this.finish();
        });
    }
}