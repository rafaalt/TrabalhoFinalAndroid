package edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.ImageUtils;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.ReceitasInit;

public class AppDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB__RECEITAS";
    private static final int DB_VERSION = 1;
    private ImageUtils imageUtils;
    public AppDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CategoryDAO.CREATE_SCRIPT);
        db.execSQL(ReceitaDAO.CREATE_SCRIPT);
        db.execSQL(ReceitasInit.CREATE_RECEITA1);
        db.execSQL(ReceitasInit.CREATE_RECEITA2);
        db.execSQL(ReceitasInit.CREATE_RECEITA3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
