package edu.coltec.dupla.rafaelgabriel.trabalhofinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.bll.ReceitaBLL;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils.ImageUtils;

public class AppDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_RECEITASAAB";
    private static final int DB_VERSION = 1;
    private ImageUtils imageUtils;
    public AppDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        imageUtils = new ImageUtils(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CategoryDAO.CREATE_SCRIPT);
        db.execSQL(ReceitaDAO.CREATE_SCRIPT);
        db.execSQL(ReceitaDAO.CREATE_RECEITA1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
