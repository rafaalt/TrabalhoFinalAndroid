package edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Receita implements Serializable {

    private int id;
    private String nome;
    private String autor;
    private ArrayList<String> ingredientes;
    private String modoDePreparo;
    private ArrayList<String> categoria;
    private int dificuldade;
    private String fotoDaReceita;

    public Receita(String nome, String autor, ArrayList<String> categoria, ArrayList<String> ingredientes, String modoDePreparo, int dificuldade, String fotoDaReceita) {
        this.autor = autor;
        this.nome = nome;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.dificuldade = dificuldade;
        this.fotoDaReceita = fotoDaReceita;
    }

    public Receita(String nome, String autor, ArrayList<String> categoria, ArrayList<String> ingredientes, String modoDePreparo, int dificuldade) {
        this.autor = autor;
        this.nome = nome;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.dificuldade = dificuldade;
        String nomeSemEspaco = nome.replaceAll("\\s+","");
        this.fotoDaReceita = nomeSemEspaco + ".jpeg";
    }
    public Receita(int id, String nome, String autor, ArrayList<String> categoria, ArrayList<String> ingredientes, String modoDePreparo, int dificuldade, String fotoDaReceita) {
        this.id = id;
        this.autor = autor;
        this.nome = nome;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.dificuldade = dificuldade;
        this.fotoDaReceita = fotoDaReceita;
    }

    public String getFotoDaReceita() {
        return fotoDaReceita;
    }

    public void setFotoDaReceita(String fotoDaReceita) {
        this.fotoDaReceita = fotoDaReceita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public ArrayList<String> getCategoria() {
        return categoria;
    }

    public void setCategoria(ArrayList<String> categoria) {
        this.categoria = categoria;
    }

    //Verifica se duas receitas sao iguais


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return nome.equals(receita.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public boolean isValid() {
        if (this.nome == null || this.nome.equals(""))
            return false;
        if (this.autor == null || this.autor.equals(""))
            return false;
        if (this.categoria.isEmpty())
            return false;
        if (this.dificuldade == 0)
            return false;
        if (this.modoDePreparo == null || this.modoDePreparo.equals(""))
            return false;
        if (this.ingredientes.isEmpty())
            return false;

        return true;
    }

    public boolean contemCategoria(String cat){
        if(this.categoria.contains(cat))
            return true;
        else
            return false;
    }

    public boolean contemTrechoNome(String x){
        if(x.equals(""))
            return true;
        if(this.nome.toLowerCase().contains(x.toLowerCase()))
            return true;

        return false;
    }
}
