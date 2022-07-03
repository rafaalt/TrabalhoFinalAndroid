package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;

public class ReceitaAdapter extends BaseAdapter {

    private Context context;
    private List<Receita> receitas;

    public ReceitaAdapter(Context context) {
        AppDB appDB = new AppDB(context);
        ReceitaBLL receitaBLL = new ReceitaBLL(appDB);

        this.context = context;
        this.receitas = receitaBLL.getAll();
    }
    public void updateList() {
        AppDB appDB = new AppDB(context);
        ReceitaBLL receitaBLL = new ReceitaBLL(appDB);
        this.receitas = receitaBLL.getAll();
    }

    @Override
    public int getCount() {
        return this.receitas.size();
    }

    @Override
    public Object getItem(int i) {
        return this.receitas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.receitas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Receita receitaAtual = this.receitas.get(i);

        View retorno = LayoutInflater.from(this.context).inflate(R.layout.adapter_receita, viewGroup, false);
        TextView nomeReceita = retorno.findViewById(R.id.adapterNome);
        TextView autorReceita = retorno.findViewById(R.id.adapterAutor);
        RatingBar ratingBar = retorno.findViewById(R.id.adapterDificuldade);

        nomeReceita.setText(receitaAtual.getNome());
        autorReceita.setText(receitaAtual.getAutor());
        ratingBar.setRating(receitaAtual.getDificuldade());

        return retorno;
    }
}