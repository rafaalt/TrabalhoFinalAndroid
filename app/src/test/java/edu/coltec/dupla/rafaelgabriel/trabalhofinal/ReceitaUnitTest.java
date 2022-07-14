package edu.coltec.dupla.rafaelgabriel.trabalhofinal;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao.Receita;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReceitaUnitTest {
    @Test
    public void receita_isEqualByName(){
        //Nomes Iguais = Receitas Iguais
            ArrayList<String> categorias = new ArrayList<>();
            categorias.add("Bolos");
            categorias.add("Outros");
            ArrayList<String> categorias2 = new ArrayList<>();
            categorias2.add("Massas");
            ArrayList<String> ingredientes = new ArrayList<>();
            ArrayList<String> ingredientes2 = new ArrayList<>();
            ingredientes.add("Ovo");
            ingredientes2.add("Leite");
            Receita receita1 = new Receita("NomeA", "Rafael", ingredientes, categorias, "Modo de Preparo", 1);
            Receita receita2 = new Receita("NomeA", "Gabriel", ingredientes2, categorias2, "Modo de Preparo2", 5);

            assertEquals(receita1, receita2);

            receita2 = new Receita("NomeB", "Rafael", ingredientes, categorias, "Modo de Preparo", 1);
            assertNotEquals(receita1, receita2);
    }

    @Test
    public void receita_isValid(){
        //Valida = Nao possui valores vazios ou nulos na Receita
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Bolos");
        categorias.add("Outros");
        ArrayList<String> ingredientes = new ArrayList<>();
        ingredientes.add("Ovo");
        Receita receita1 = new Receita("NomeA", "Rafael", categorias, ingredientes, "Modo de Preparo", 1);
        assertTrue(receita1.isValid());

        Receita receita2 = new Receita("NomeA", "Gabriel", categorias, new ArrayList<>(),"Modo de Preparo2", 5);
        assertFalse(receita2.isValid());
    }

    @Test
    public void receita_contemCategoria(){
        //Receita contem Categoria
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Bolos");
        categorias.add("Outros");
        ArrayList<String> ingredientes = new ArrayList<>();
        ingredientes.add("Ovo");
        Receita receita1 = new Receita("NomeA", "Rafael", categorias, ingredientes, "Modo de Preparo", 1);

        assertTrue(receita1.contemCategoria("Bolos"));

        assertFalse(receita1.contemCategoria("Massas"));
    }

    @Test
    public void receita_nomeComtem(){
        //Nome da Receita contem X
        ArrayList<String> categorias = new ArrayList<>();
        categorias.add("Bolos");
        ArrayList<String> ingredientes = new ArrayList<>();
        ingredientes.add("Ovo");
        Receita receita1 = new Receita("NomeA", "Rafael", categorias, ingredientes, "Modo de Preparo", 1);

        assertTrue(receita1.contemTrechoNome("ea"));

        assertFalse(receita1.contemCategoria("eao"));
    }
}