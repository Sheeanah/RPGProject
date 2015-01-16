package com.example.rpgproject.rpgproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rpgproject.rpgproject.modele.Joueur;

/**
 * Created by Dorian on 16/01/2015.
 */
public class RPGProjectDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "RPGProject.db";
    private static final int DATABASE_VERSION = 2;
    private static final String MYBASE_TABLEjoueur_NAME="joueur";
    private static final String MYBASE_TABLEinvent_NAME="inventaire";
    private static final String MYBASE_TABLEjoueur_CREATE = "CREATE TABLE IF NOT EXISTS `"+MYBASE_TABLEjoueur_NAME+"` (`idjoueur` int(11) NOT NULL AUTO_INCREMENT,`or` int(11) NOT NULL,`xp` int(11) NOT NULL)";
    private static final String MYBASE_TABLEinvent_CREATE="CREATE TABLE IF NOT EXISTS `"+MYBASE_TABLEinvent_NAME+"` (`idjoueur` int(11) NOT NULL,`idobjet` int(11) NOT NULL)";

    RPGProjectDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MYBASE_TABLEjoueur_CREATE);
        db.execSQL(MYBASE_TABLEinvent_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveJoueur(Context context,Joueur joueur){
        RPGProjectDB myDbHelper=new RPGProjectDB(context);
        SQLiteDatabase myDb=myDbHelper.getWritableDatabase();
        String query="INSERT INTO";

    }
    public Joueur getJoueur(int idJoueur){
        return null;
    }
}
