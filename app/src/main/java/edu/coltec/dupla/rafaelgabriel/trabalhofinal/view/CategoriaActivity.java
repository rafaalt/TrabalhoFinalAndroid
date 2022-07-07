package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.AppCompat;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.IdiomaManager;

public class CategoriaActivity extends AppCompat {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);


        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        ImageView brasil_idioma = findViewById(R.id.img_brasil);
        ImageView en_idioma = findViewById(R.id.img_en);
        bottomNav.setSelectedItemId(R.id.categorias_bottomNav);
        IdiomaManager idiomaManager = new IdiomaManager(this);

        brasil_idioma.setOnClickListener(view ->{
            idiomaManager.updateResource("pt");
            recreate();
        });

        en_idioma.setOnClickListener(view ->{
            idiomaManager.updateResource("en");
            recreate();
        });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.receitas_bottomNav:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.categorias_bottomNav:
                        return true;
                    case R.id.sair_bottomNav:
                        finish();
                        System.exit(0);
                        return true;
                }
                return false;
            }
        });


    }
}