package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.AppCompat;

public class MainActivity extends AppCompat {

    ReceitaAdapter receitaAdapter;
    ArrayList<String> categoriasSelecionadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab_telaDeCadastro = findViewById(R.id.floating_action_button);

        categoriasSelecionadas = new ArrayList<>();
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.receitas_bottomNav);

        ListView lista = this.findViewById(R.id.mainList);
        receitaAdapter = new ReceitaAdapter(this);
        lista.setAdapter(receitaAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Receita receita = (Receita ) receitaAdapter.getItem(i);

                Intent intent = new Intent(MainActivity.this, ReceitaDetailActivity.class);
                intent.putExtra("receita", receita);
                startActivity(intent);
            }
        });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.receitas_bottomNav:
                        return true;
                    case R.id.categorias_bottomNav:
                        startActivity(new Intent(getApplicationContext(), CategoriaActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.sair_bottomNav:
                        finish();
                        System.exit(0);
                        return true;
                }
                return false;
            }
        });

        //Navegar para tela cadastro de receitas
        fab_telaDeCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCadastroActivity = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(goToCadastroActivity);
            }
        });
    }

    //Action Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                receitaAdapter.uptadeListByName(s);
                receitaAdapter.notifyDataSetChanged();
                return false;
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                return true;
            case R.id.action_refresh:
                AlertDialog.Builder alertBuilder1 = new AlertDialog.Builder(this);

                alertBuilder1.setIcon(R.drawable.ic_baseline_emoji_events_24);
                alertBuilder1.setTitle(R.string.VersaoPremio);
                alertBuilder1.setMessage(R.string.AcessoPremio);

                alertBuilder1.setPositiveButton(R.string.BtnEntendi, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getBaseContext(), "Você saiu da versão premio", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog1 = alertBuilder1.create();
                dialog1.show();
                return true;
            case R.id.action_about:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

                alertBuilder.setIcon(R.drawable.ic_baseline_restaurant_24);
                alertBuilder.setTitle(R.string.AlertAbout);
                alertBuilder.setMessage(R.string.ConteudoAbout);

                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getBaseContext(), "Você saiu de About", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = alertBuilder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.mainCheck1:
                //Toast.makeText(this, "Bolos", Toast.LENGTH_SHORT).show();
                if (checked)
                    this.categoriasSelecionadas.add("Bolos");
                else {
                    if (this.categoriasSelecionadas.contains("Bolos"))
                        this.categoriasSelecionadas.remove("Bolos");
                }
                break;
            case R.id.mainCheck2:
                if (checked)
                    this.categoriasSelecionadas.add("Carnes");
                else {
                    if (this.categoriasSelecionadas.contains("Carnes"))
                        this.categoriasSelecionadas.remove("Carnes");
                }
                break;
            case R.id.mainCheck3:
                if (checked)
                    this.categoriasSelecionadas.add("Saladas");
                else {
                    if (this.categoriasSelecionadas.contains("Saladas"))
                        this.categoriasSelecionadas.remove("Saladas");
                }
                break;
            case R.id.mainCheck4:
                if (checked)
                    this.categoriasSelecionadas.add("Bebidas");
                else {
                    if (this.categoriasSelecionadas.contains("Bebidas"))
                        this.categoriasSelecionadas.remove("Bebidas");
                }
                break;
            case R.id.mainCheck5:
                if (checked)
                    this.categoriasSelecionadas.add("Doces");
                else {
                    if (this.categoriasSelecionadas.contains("Doces"))
                        this.categoriasSelecionadas.remove("Doces");
                }
                break;
            case R.id.mainCheck6:
                if (checked)
                    this.categoriasSelecionadas.add("Peixes");
                else {
                    if (this.categoriasSelecionadas.contains("Peixes"))
                        this.categoriasSelecionadas.remove("Peixes");
                }
                break;
            case R.id.mainCheck7:
                if (checked)
                    this.categoriasSelecionadas.add("Massas");
                else {
                    if (this.categoriasSelecionadas.contains("Massas"))
                        this.categoriasSelecionadas.remove("Massas");
                }
                break;
            case R.id.mainCheck8:
                if (checked)
                    this.categoriasSelecionadas.add("Outros");
                else {
                    if (this.categoriasSelecionadas.contains("Outros"))
                        this.categoriasSelecionadas.remove("Outros");
                }
                break;
            default:
                break;

        }

        receitaAdapter.updateList(categoriasSelecionadas);
        receitaAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        receitaAdapter.updateList(categoriasSelecionadas);
        receitaAdapter.notifyDataSetChanged();
    }
}