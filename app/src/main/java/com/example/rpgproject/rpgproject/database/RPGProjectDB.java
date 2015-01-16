package com.example.rpgproject.rpgproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dorian on 16/01/2015.
 */
public class RPGProjectDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "RPGProject.db";
    private static final int DATABASE_VERSION=2;
    private static final String MYBASE_TABLE_CREATE="";

    RPGProjectDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
