package com.example.rpgproject.rpgproject.database;

import android.content.Context;
import android.database.Cursor;
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
    private static final String MYBASE_TABLEjoueur_CREATE = "CREATE TABLE IF NOT EXISTS "+MYBASE_TABLEjoueur_NAME+" (idjoueur INTEGER NOT NULL AUTO_INCREMENT,or INTEGER NOT NULL,xp INTEGER NOT NULL)";
    private static final String MYBASE_TABLEinvent_CREATE="CREATE TABLE IF NOT EXISTS "+MYBASE_TABLEinvent_NAME+" (idjoueur INTEGER NOT NULL,idobjet INTEGER NOT NULL)";

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
        String query="INSERT INTO "+MYBASE_TABLEjoueur_NAME+"(idjoueur,or,xp) VALUES(?,"+joueur.getOr()+","+joueur.getXp()+")";
        myDb.execSQL(query);
        myDb.close();
        myDbHelper.close();


    }
    public Joueur getJoueur(Context context,int idJoueur){
        int xp=0,or=0;
        Joueur res;
        RPGProjectDB myDbHelper=new RPGProjectDB(context);
        SQLiteDatabase myDb=myDbHelper.getReadableDatabase();
        String query="SELECT or,xp FROM "+MYBASE_TABLEjoueur_NAME+" WHERE idjoueur="+idJoueur;
        Cursor result= myDb.rawQuery(query,null);
        if(result!=null){
            while(result.moveToNext()){
                or=result.getInt(0);
                xp=result.getInt(1);
            }
            res=new Joueur(idJoueur,xp,or);
        }
        else{
            res=null;
        }
        myDb.close();
        myDbHelper.close();
        return res;
    }
}
