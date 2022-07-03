package edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoryDAO {

    public static final String CREATE_SCRIPT =
            "CREATE TABLE IF NOT EXISTS categories (\n" +
                    "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tname TEXT NOT NULL\n" +
                    ");";


    public static final String TABLE_NAME = "categories";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";

    private AppDB appDB;

    public CategoryDAO(AppDB appDB) {
        this.appDB = appDB;
    }


    @SuppressLint("Range")
    public Category getById(Category category) {
        Category result = null;
        SQLiteDatabase readDb = this.appDB.getReadableDatabase();

        try {
            String clause = ID_COLUMN + "=?";
            String[] params = { category.getId() + "" };
            Cursor c = readDb.query(TABLE_NAME, null, clause, params, null, null, null, null);

            if (c.moveToFirst()) {
                int id = c.getInt(c.getColumnIndex(ID_COLUMN));
                String name = c.getString(c.getColumnIndex(NAME_COLUMN));

                result = new Category(id, name);
            }
        } catch (Exception e) {
            result = null;
        } finally {
            readDb.close();
        }
        return result;
    }

    @SuppressLint("Range")
    public Category getByName(Category category) {
        Category result = null;
        SQLiteDatabase readDb = this.appDB.getReadableDatabase();

        try {
            String clause = NAME_COLUMN + "=?";
            String[] params = { category.getName() + "" };
            Cursor c = readDb.query(TABLE_NAME, null, clause, params, null, null, null, null);

            if (c.moveToFirst()) {
                int id = c.getInt(c.getColumnIndex(ID_COLUMN));
                String name = c.getString(c.getColumnIndex(NAME_COLUMN));

                result = new Category(id, name);
            }
        } catch (Exception e) {
            result = null;
        } finally {
            readDb.close();
        }
        return result;
    }

    public boolean create(Category category) {
        boolean result = true;
        SQLiteDatabase writeDb = this.appDB.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(NAME_COLUMN, category.getName());
            writeDb.insert(TABLE_NAME, null, values);
        } catch(Exception e) {
            result = false;
        } finally {
            writeDb.close();
        }
        return result;
    }
}
