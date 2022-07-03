package edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll;

import java.util.ArrayList;
import java.util.List;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.AppDB;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Category;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.CategoryDAO;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.ReceitaDAO;

public class ReceitaBLL {

    private AppDB appDB;

    public ReceitaBLL(AppDB appDB) {
        this.appDB = appDB;
    }

    public boolean create(String name, String description, String categoryName, ArrayList<String> ingredientes, String modoPreparo, int dificuldade) {
        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);
        CategoryDAO categoryDAO = new CategoryDAO(this.appDB);

        Category category = categoryDAO.getByName(new Category(categoryName));
        if (category == null) {
            categoryDAO.create(new Category(categoryName));
            category = categoryDAO.getByName(new Category(categoryName));
        }

        ArrayList<String> array = new ArrayList<>();
        array.add("alo");
        Receita newTask = new Receita(name, description, category, ingredientes, modoPreparo, dificuldade);


        if (!newTask.isValid())
            return false;

        Receita task = receitaDAO.getByName(newTask);
        if (task != null)
            return false;

        boolean result = receitaDAO.create(newTask);
        return result;
    }

    public List<Receita> getAll() {
        List<Receita> tasks;

        ReceitaDAO taskDAO = new ReceitaDAO(this.appDB);
        tasks = taskDAO.getAll();

        return tasks;
    }

    public boolean delete(Receita receita){
        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);
        return receitaDAO.delete(receita);
    }

}
