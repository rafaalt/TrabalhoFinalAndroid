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

    public boolean create(String name, String description, ArrayList<String> categorias, ArrayList<String> ingredientes, String modoPreparo, int dificuldade, String foto) {
        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);

        Receita newTask = new Receita(name, description, categorias, ingredientes, modoPreparo, dificuldade, foto);

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

    public static void adicionarR1(AppDB appDB1){
        String nome = "Bolo Simples";
        String autor = "Maria Vechi";
        ArrayList<String> ingredientes = new ArrayList<>();
        ingredientes.add("2 xícaras de açucar");
        ingredientes.add("3 xícaras de farinha de trigo");
        ingredientes.add("4 colheres de sopa de margarina");
        ingredientes.add("3 ovos");
        ingredientes.add("1 e 1/2 xícara de leite");
        ingredientes.add("1 colher de sopa bem cheia de fermento em pó");

        String modo = "Bata as claras em neve e reserve.\n" +
                "Misture as gemas, a margarina e o açúcar até obter uma massa homogênea.\n" +
                "Acrescente o leite e a farinha de trigo aos poucos, sem parar de bater.\n" +
                "Por último, adicione as claras em neve e o fermento.\n" +
                "Despeje a massa em uma forma grande de furo central untada e enfarinhada.\n" +
                "Asse em forno médio 180 °C, preaquecido, por aproximadamente 40 minutos ou ao furar o bolo com um garfo, este saia limpo.\n";
        int dificuldade = 3;
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Bolos");
        categorias.add("Lanches");
        Receita receitaBolo = new Receita(nome, autor, categorias, ingredientes, modo, dificuldade, "BoloSimples.jpeg");
        ReceitaDAO receitaDAO = new ReceitaDAO(appDB1);
        receitaDAO.create(receitaBolo);
    }
    public boolean delete(Receita receita){
        ReceitaDAO receitaDAO = new ReceitaDAO(this.appDB);
        return receitaDAO.delete(receita);
    }

}
