package edu.coltec.dupla.rafaelgabriel.trabalhofinal;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Receita {
    private String nome;
    private String autor;
    private String fotoUrl;
    private ArrayList<String> ingredientes;
    private String modoDePreparo;
    private ArrayList<String> categoria;
    private int dificuldade;

    public Receita(String nome, String autor, String fotoUrl, ArrayList<String> categoria, ArrayList<String> ingredientes, String modoDePreparo, int dificuldade) {
        this.fotoUrl = fotoUrl;
        this.autor = autor;
        this.nome = nome;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.dificuldade = dificuldade;
    }

    public Receita(String nome, String autor, ArrayList<String> categoria, ArrayList<String> ingredientes, String modoDePreparo, int dificuldade) {
        this.fotoUrl = "";
        this.autor = autor;
        this.nome = nome;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.dificuldade = dificuldade;
    }

    public Receita(){
        this.fotoUrl = "";
        this.autor =  "";
        this.nome =  "";
        this.categoria = new ArrayList<>();
        this.ingredientes = new ArrayList<>();
        this.modoDePreparo = "";
        this.dificuldade = 0;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
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

    //Verifica se duas receitas sao iguais
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receita receita = (Receita) o;
        return Objects.equals(autor, receita.autor) && Objects.equals(nome, receita.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autor, nome);
    }
}
