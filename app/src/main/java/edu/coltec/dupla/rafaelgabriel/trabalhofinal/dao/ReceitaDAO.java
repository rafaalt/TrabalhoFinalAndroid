package edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public static final String CREATE_SCRIPT =
            "CREATE TABLE IF NOT EXISTS receitas (\n" +
                    "\tid INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                    "\tname TEXT NOT NULL, \n" +
                    "\tautor TEXT NOT NULL, \n" +
                    "\tingredientes TEXT NOT NULL, \n" +
                    "\tmododepreparo TEXT NOT NULL, \n" +
                    "\tdificuldade INTEGER NOT NULL, \n" +
                    "\tcategorias TEXT, \n" +
                    "\tfotoReceita TEXT\n" +
                    ");";

    public static String modo = "Bata as claras em neve e reserve.\n" +
            "Misture as gemas, a margarina e o açúcar até obter uma massa homogênea.\n" +
            "Acrescente o leite e a farinha de trigo aos poucos, sem parar de bater.\n" +
            "Por último, adicione as claras em neve e o fermento.\n" +
            "Despeje a massa em uma forma grande de furo central untada e enfarinhada.\n" +
            "Asse em forno médio 180 °C, preaquecido, por aproximadamente 40 minutos ou ao furar o bolo com um garfo, este saia limpo.\n";

    public static final String CREATE_RECEITA1 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (1, \"Bolo Simples\", \"Maria Vechi\", \n" +
            "\"2 xícaras de açucar#3 xícaras de farinha de trigo#4 colheres de sopa com margarina#3 ovos#1 e 1/2 xícara de leite#1 colher de sopa bem cheia de fermento em pó\", \n\"" +
            modo + "\", 3, \"Bolos#Lanches\",\"BoloSimples.jpeg\");";

    private static final String TABLE_NAME = "receitas";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String CATEGORY_COLUMN = "categorias";
    private static final String DIFICULDADE_COLUMN = "dificuldade";
    private static final String AUTOR_COLUMN = "autor";
    private static final String INGREDIENTES_COLUMN = "ingredientes";
    private static final String MODOPREPARO_COLUMN = "mododepreparo";
    private static final String FOTO_COLUMN = "fotoReceita";


    private AppDB appDB;

    public ReceitaDAO(AppDB appDB) {
        this.appDB = appDB;
    }


    public boolean create(Receita receita) {
        boolean result = true;
        SQLiteDatabase writeDb = this.appDB.getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME_COLUMN, receita.getNome());
            contentValues.put(AUTOR_COLUMN, receita.getAutor());
            contentValues.put(DIFICULDADE_COLUMN, receita.getDificuldade());

            String ingredientes = "";
            for(String x : receita.getIngredientes()) {
                ingredientes += x;
                ingredientes += "#";
            }
            String categorias = "";
            for(String x : receita.getCategoria()) {
                categorias += x;
                categorias += "#";
            }
            contentValues.put(INGREDIENTES_COLUMN, ingredientes);
            contentValues.put(MODOPREPARO_COLUMN, receita.getModoDePreparo());
            contentValues.put(CATEGORY_COLUMN, categorias);
            contentValues.put(FOTO_COLUMN, receita.getFotoDaReceita());
            writeDb.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            result = false;
        } finally {
            writeDb.close();
        }

        return result;
    }


    @SuppressLint("Range")
    public Receita getByName(Receita task) {
        Receita searchedTask = null;
        CategoryDAO categoryDAO = new CategoryDAO(this.appDB);
        SQLiteDatabase readDB = this.appDB.getReadableDatabase();

        try {

            String clause = NAME_COLUMN + "=?";
            String[] values = { task.getNome() };
            Cursor res = readDB.query(TABLE_NAME, null, clause, values, null, null, null);

            if (res.moveToFirst()) {
                int id = res.getInt(res.getColumnIndex(ID_COLUMN));
                String name = res.getString(res.getColumnIndex(NAME_COLUMN));
                String autor = res.getString(res.getColumnIndex(AUTOR_COLUMN));
                String modo = res.getString(res.getColumnIndex(MODOPREPARO_COLUMN));
                int dificuldade = res.getInt(res.getColumnIndex(DIFICULDADE_COLUMN));
                String ing = res.getString(res.getColumnIndex(INGREDIENTES_COLUMN));
                String cat = res.getString(res.getColumnIndex(CATEGORY_COLUMN));
                String foto = res.getString(res.getColumnIndex(FOTO_COLUMN));
                ArrayList<String> ingredientes = new ArrayList<>();
                String[] ingredientesSeparados = ing.split("#");
                for(int i = 0; i<ingredientesSeparados.length ; i++){
                    ingredientes.add(ingredientesSeparados[i]);
                }
                ArrayList<String> categorias = new ArrayList<>();
                String[] categoriasSeparados = ing.split("#");
                for(int i = 0; i<categoriasSeparados.length ; i++){
                    categorias.add(categoriasSeparados[i]);
                }
                searchedTask = new Receita(id, name, autor, categorias, ingredientes, modo, dificuldade, foto);
            }
        } catch(Exception e) {
            searchedTask = null;
        } finally {
            readDB.close();
        }

        return  searchedTask;
    }

    @SuppressLint("Range")
    public List<Receita> getAll() {
        List<Receita> tasks = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO(this.appDB);
        SQLiteDatabase readDB = this.appDB.getReadableDatabase();

        try {
            Cursor res = readDB.query(TABLE_NAME, null, null, null, null, null, null);
            if (res.moveToFirst()) {
                do {
                    int id = res.getInt(res.getColumnIndex(ID_COLUMN));
                    String name = res.getString(res.getColumnIndex(NAME_COLUMN));
                    String autor = res.getString(res.getColumnIndex(AUTOR_COLUMN));
                    String modo = res.getString(res.getColumnIndex(MODOPREPARO_COLUMN));
                    int dificuldade = res.getInt(res.getColumnIndex(DIFICULDADE_COLUMN));
                    String ing = res.getString(res.getColumnIndex(INGREDIENTES_COLUMN));
                    String cat = res.getString(res.getColumnIndex(CATEGORY_COLUMN));
                    String foto = res.getString(res.getColumnIndex(FOTO_COLUMN));
                    ArrayList<String> ingredientes = new ArrayList<>();
                    String[] ingredientesSeparados = ing.split("#");
                    for(int i = 0; i<ingredientesSeparados.length ; i++){
                        ingredientes.add(ingredientesSeparados[i]);
                    }
                    ArrayList<String> categorias = new ArrayList<>();
                    String[] categoriasSeparados = cat.split("#");
                    for(int i = 0; i<categoriasSeparados.length ; i++){
                        categorias.add(categoriasSeparados[i]);
                    }

                    tasks.add(new Receita(id, name, autor, categorias, ingredientes, modo, dificuldade, foto));
                } while (res.moveToNext());
            }
        } catch(Exception e) {
            tasks = null;
        } finally {
            readDB.close();
        }
        return tasks;
    }

    public boolean delete(Receita receita){
        boolean result = true;
        SQLiteDatabase removeDb = this.appDB.getWritableDatabase();

        try {
            int id = receita.getId();
            String whereClause = "id=?";
            String[] params = new String[] { String.valueOf(id) };
            removeDb.delete(TABLE_NAME, whereClause, params);
        } catch (Exception e) {
            result = false;
        } finally {
            removeDb.close();
        }

        return result;
    }
}
