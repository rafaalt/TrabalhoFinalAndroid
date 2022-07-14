package edu.coltec.dupla.rafaelgabriel.trabalhofinal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;

public class ReceitaAdapter extends BaseAdapter {

    private Context context;
    private List<Receita> receitas;
    private List<Receita> receitasCategoria;
    private List<Receita> receitasExibicao;
    private String momento;

    public ReceitaAdapter(){}

    public ReceitaAdapter(Context context) {
        AppDB appDB = new AppDB(context);
        ReceitaBLL receitaBLL = new ReceitaBLL(appDB);
        this.momento = "";
        this.context = context;
        this.receitas = receitaBLL.getAll();
        this.receitasCategoria = this.receitas;
        this.receitasExibicao = this.receitas;
    }

    public void uptadeListByName(String str){
        this.momento = str;
        ArrayList<Receita> novasR = new ArrayList<>();
        for(Receita x : receitasCategoria){
            if(x.contemTrechoNome(str)){
                novasR.add(x);
            }
        }
        receitasExibicao = novasR;
    }

    public void updateList(ArrayList<String> categorias) {
        AppDB appDB = new AppDB(context);
        ReceitaBLL receitaBLL = new ReceitaBLL(appDB);
        this.receitas = receitaBLL.getAll();
        this.receitasCategoria = new ArrayList<>();

        if(categorias.isEmpty()){
            this.receitasCategoria = this.receitas;
        }

        else{
            for(Receita x : this.receitas) {
                for (String y : categorias) {
                    if(x.contemCategoria(y)){
                        this.receitasCategoria.add(x);
                    }
                }
            }
        }
        uptadeListByName(momento);
    }

    @Override
    public int getCount() {
        return this.receitasExibicao.size();
    }

    @Override
    public Object getItem(int i) {
        return this.receitasExibicao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.receitasExibicao.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Receita receitaAtual = this.receitasExibicao.get(i);

        View retorno = LayoutInflater.from(this.context).inflate(R.layout.adapter_receita, viewGroup, false);
        TextView nomeReceita = retorno.findViewById(R.id.adapterNome);
        TextView autorReceita = retorno.findViewById(R.id.adapterAutor);
        RatingBar ratingBar = retorno.findViewById(R.id.adapterDificuldade);
        ImageView imageView = retorno.findViewById(R.id.adapterImagem);
        nomeReceita.setText(receitaAtual.getNome());
        autorReceita.setText(receitaAtual.getAutor());
        ratingBar.setRating(receitaAtual.getDificuldade());

        return retorno;
    }
}