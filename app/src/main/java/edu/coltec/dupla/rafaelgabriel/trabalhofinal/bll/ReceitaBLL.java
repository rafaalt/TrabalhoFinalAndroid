package edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll;

import java.util.ArrayList;
import java.util.List;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.ReceitaDAO;

public class ReceitaBLL {

    private AppDB appDB;


    public ReceitaBLL(AppDB appDB) {
        this.appDB = appDB;
    }

    public boolean create(String name, String description, ArrayList<String> categorias, ArrayList<String> ingredientes, String modoPreparo, int dificuldade, String foto) {
        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);

        Receita receita = new Receita(name, description, categorias, ingredientes, modoPreparo, dificuldade, foto);

        if (!receita.isValid())
            return false;

        Receita novaReceita = receitaDAO.getByName(receita);
        if (novaReceita != null)
            return false;

        boolean result = receitaDAO.create(receita);
        return result;
    }

    public List<Receita> getAll() {
        List<Receita> receitas;

        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);
        receitas = receitaDAO.getAll();

        return receitas;
    }

    public boolean delete(Receita receita){
        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);
        return receitaDAO.delete(receita);
    }

}
