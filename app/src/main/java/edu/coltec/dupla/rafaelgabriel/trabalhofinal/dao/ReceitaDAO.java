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
                    "\tcategory_id INTEGER NOT NULL, \n" +
                    "\n" +
                    "\tFOREIGN KEY(category_id) REFERENCES categories(id)\n" +
                    ");";

    private static final String TABLE_NAME = "receitas";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String CATEGORY_COLUMN = "category_id";
    private static final String DIFICULDADE_COLUMN = "dificuldade";
    private static final String AUTOR_COLUMN = "autor";
    private static final String INGREDIENTES_COLUMN = "ingredientes";
    private static final String MODOPREPARO_COLUMN = "mododepreparo";


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
            //Ingredientes separados por #
            String ingredientes = "";
            for(String x : receita.getIngredientes()) {
                ingredientes += x;
                ingredientes += "#";
            }
            contentValues.put(INGREDIENTES_COLUMN, ingredientes);
            contentValues.put(MODOPREPARO_COLUMN, receita.getModoDePreparo());
            contentValues.put(CATEGORY_COLUMN, receita.getCategoria().getId());
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

                ArrayList<String> ingredientes = new ArrayList<>();
                String[] ingredientesSeparados = ing.split("#");
                for(int i = 0; i<ingredientesSeparados.length ; i++){
                    ingredientes.add(ingredientesSeparados[i]);
                }

                int categoryId = res.getInt(res.getColumnIndex(CATEGORY_COLUMN));
                Category category = categoryDAO.getById(new Category(categoryId));
                searchedTask = new Receita(id, name, autor, category, ingredientes, modo, dificuldade);
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

                    ArrayList<String> ingredientes = new ArrayList<>();
                    String[] ingredientesSeparados = ing.split("#");
                    for(int i = 0; i<ingredientesSeparados.length ; i++){
                        ingredientes.add(ingredientesSeparados[i]);
                    }

                    int categoryId = res.getInt(res.getColumnIndex(CATEGORY_COLUMN));
                    Category category = categoryDAO.getById(new Category(categoryId));

                    tasks.add(new Receita(id, name, autor, category, ingredientes, modo, dificuldade));
                } while (res.moveToNext());
            }
        } catch(Exception e) {
            tasks = null;
        } finally {
            readDB.close();
        }
        return tasks;
    }
}
