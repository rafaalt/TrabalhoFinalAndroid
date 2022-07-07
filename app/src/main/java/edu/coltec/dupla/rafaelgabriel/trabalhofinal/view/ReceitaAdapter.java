package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;

public class ReceitaAdapter extends BaseAdapter {

    private Context context;
    private List<Receita> receitas;

    public ReceitaAdapter(){}

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
        ImageView imageView = retorno.findViewById(R.id.adapterImagem);
        try {
            String filename = receitaAtual.getFotoDaReceita();
            ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File file = new File(directory, filename);
            imageView.setImageDrawable(Drawable.createFromPath(file.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        nomeReceita.setText(receitaAtual.getNome());
        autorReceita.setText(receitaAtual.getAutor());
        ratingBar.setRating(receitaAtual.getDificuldade());

        return retorno;
    }
}