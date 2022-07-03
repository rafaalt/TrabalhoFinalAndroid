package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;

public class MainActivity extends AppCompatActivity {

    ReceitaAdapter receitaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab_telaDeCadastro = findViewById(R.id.floating_action_button);

        ListView tasksList = this.findViewById(R.id.mainList);
        receitaAdapter = new ReceitaAdapter(this);
        tasksList.setAdapter(receitaAdapter);

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
                Toast.makeText(MainActivity.this, "Buscar o texto: " + s, Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                Toast.makeText(this, "Clicou no search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_refresh:
                Toast.makeText(this, "Clicou no refresh", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Clicou no settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_exit:
                finish();
                System.exit(0);
                return true;
            case R.id.action_about:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

                alertBuilder.setIcon(R.drawable.ic_launcher_foreground);
                alertBuilder.setTitle("Receitas Agenda Facil");
                alertBuilder.setMessage("Dupla Gabriel e Rafael  Coltec - UFMG, Trabalho Final");

                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "Você saiu de About", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = alertBuilder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        receitaAdapter.updateList();
        receitaAdapter.notifyDataSetChanged();
    }
}