package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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

        TextView txt = findViewById(R.id.detailTxt);
        Button btnRemover = findViewById(R.id.detailBtnRemover);
        Intent intent = getIntent();
        Receita receita = (Receita) intent.getSerializableExtra("receita");

        txt.setText(receita.getNome());

        btnRemover.setOnClickListener(view -> {
            if(receitaBLL.delete(receita))
                this.finish();
        });
    }
}