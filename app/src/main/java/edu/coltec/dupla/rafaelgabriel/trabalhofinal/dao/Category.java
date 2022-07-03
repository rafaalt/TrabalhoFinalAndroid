package edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao;

import java.io.Serializable;

public class Category implements Serializable {

    private Integer id;
    private String name;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(Integer id) {
        this(id, null);
    }

    public Category(String name) {
        this(null, name);
    }

    public boolean isValid() {
        return name != null && !name.equals("");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}