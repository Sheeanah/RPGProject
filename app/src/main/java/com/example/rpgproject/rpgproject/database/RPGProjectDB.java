package com.example.rpgproject.rpgproject.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.modele.Joueur;
import com.example.rpgproject.rpgproject.modele.Objets.Objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 16/01/2015.
 */
public class RPGProjectDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "RPGProject.db";
    private static final int DATABASE_VERSION = 2;
    private static final String MYBASE_TABLEjoueur_NAME="joueur";
    private static final String MYBASE_TABLEinvent_NAME="inventaire";
    private static final String MYBASE_TABLEjoueur_CREATE =
            "CREATE TABLE IF NOT EXISTS "+MYBASE_TABLEjoueur_NAME+" " +
                    "(idjoueur INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL,"+
                    "gold INTEGER NOT NULL," +
                    "xp INTEGER NOT NULL)";
    private static final String MYBASE_TABLEinvent_CREATE=
            "CREATE TABLE IF NOT EXISTS "+MYBASE_TABLEinvent_NAME+
                    " (idjoueur INTEGER NOT NULL," +
                    "idobjet INTEGER NOT NULL, " +
                    "PRIMARY KEY (idjoueur,idobjet))";
    private static final String MYBASE_JOUEUR_init="INSERT INTO "+MYBASE_TABLEjoueur_NAME+"(xp,gold,nom) VALUES(0,0,'JeanBobby')";

    RPGProjectDB(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MYBASE_TABLEjoueur_CREATE);
        db.execSQL(MYBASE_TABLEinvent_CREATE);
        db.execSQL(MYBASE_JOUEUR_init);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static List<Joueur> getListJoueurs(Context context){
        List<Joueur> res=new ArrayList<Joueur>();
        try{
            RPGProjectDB myDbHelper=new RPGProjectDB(context);
            SQLiteDatabase myDb=myDbHelper.getReadableDatabase();
            String queryJoueurs="SELECT idjoueur,xp,gold,nom FROM "+MYBASE_TABLEjoueur_NAME;
            Cursor resultJoueur=myDb.rawQuery(queryJoueurs,null);
            if(resultJoueur!=null) {
                while (resultJoueur.moveToNext()) {
                    res.add(new Joueur(resultJoueur.getInt(0), resultJoueur.getInt(1), resultJoueur.getInt(2),resultJoueur.getString(3)));
                    Log.i("getListJoueur",res.get(res.size()-1).toString());
                }
                FabriqueObjet fabrique=FabriqueObjet.getUniqueInstance();
                for(Joueur j : res){
                    String queryInventaire="SELECT idobjet FROM "+MYBASE_TABLEinvent_NAME+" WHERE idjoueur="+j.getId();
                    Cursor resultInventaire=myDb.rawQuery(queryInventaire,null);
                    if(resultInventaire!=null){
                        while(resultInventaire.moveToNext()){
                            Objet obj=fabrique.getObjet(resultInventaire.getInt(0),context);
                            j.equiper(obj);
                            Log.i("getListJoueur","Objet "+obj.getNom()+" equipé par "+j);
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static void addJoueur(Context context,Joueur joueur){
        try{
            RPGProjectDB myDbHelper=new RPGProjectDB(context);
            SQLiteDatabase myDb=myDbHelper.getWritableDatabase();
            String queryJoueur="INSERT INTO "+MYBASE_TABLEjoueur_NAME+"(gold,xp,nom) VALUES("+joueur.getOr()+","+joueur.getXp()+","+joueur.getNom()+")";
            myDb.execSQL(queryJoueur);
            Log.i("addJoueur",joueur+" ajouté à la base");
            FabriqueObjet fabrique=FabriqueObjet.getUniqueInstance();
            for(Objet obj : joueur.getInventaire()) {
                String queryInventaire="INSERT INTO "+MYBASE_TABLEinvent_NAME+"(idjoueur,idobjet) VALUES("+joueur.getId()+","+ fabrique.getIdObjet(obj.getClass().toString())+")";
                myDb.execSQL(queryInventaire);
                Log.i("addJoueur","Objet "+obj.getNom()+" enregistré pour "+joueur);
            }
            myDb.close();
            myDbHelper.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void saveJoueur(Context context, Joueur joueur) {
        try{
            RPGProjectDB myDbHelper=new RPGProjectDB(context);
            SQLiteDatabase myDb=myDbHelper.getWritableDatabase();
            String queryJoueur="UPDATE "+MYBASE_TABLEjoueur_NAME+" SET gold="+joueur.getOr()+",xp="+joueur.getXp()+" WHERE idjoueur="+joueur.getId();
            myDb.execSQL(queryJoueur);
            Log.i("saveJoueur",joueur+" mis à jour dans la base");
            FabriqueObjet fabrique=FabriqueObjet.getUniqueInstance();
            for(Objet obj : joueur.getInventaire()){
                String queryInventaire="INSERT INTO "+MYBASE_TABLEinvent_NAME+"(idjoueur,idobjet) VALUES("+joueur.getId()+","+fabrique.getIdObjet(obj.getClass().toString()+")");
                Log.i("saveJoueur","Objet "+obj.getNom()+" enregistré pour "+joueur);
            }
            myDb.close();
            myDbHelper.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Joueur getJoueur(Context context,int id) {
        Joueur res=null;
        try{
            RPGProjectDB myDbHelper=new RPGProjectDB(context);
            SQLiteDatabase myDb=myDbHelper.getReadableDatabase();
            String queryJoueur="SELECT idjoueur,xp,gold,nom FROM "+MYBASE_TABLEjoueur_NAME+" WHERE idjoueur="+id;
            Cursor resultJoueur=myDb.rawQuery(queryJoueur,null);
            if(resultJoueur!=null) {
                while (resultJoueur.moveToNext()) {
                    res=new Joueur(resultJoueur.getInt(0), resultJoueur.getInt(1), resultJoueur.getInt(2),resultJoueur.getString(3));
                    Log.i("getJoueur",res.toString());
                }
                FabriqueObjet fabrique=FabriqueObjet.getUniqueInstance();
                String queryInventaire="SELECT idobjet FROM "+MYBASE_TABLEinvent_NAME+" WHERE idjoueur="+res.getId();
                Cursor resultInventaire=myDb.rawQuery(queryInventaire,null);
                if(resultInventaire!=null){
                    while(resultInventaire.moveToNext()){
                        Objet obj=fabrique.getObjet(resultInventaire.getInt(0),context);
                        res.equiper(obj);
                        Log.i("getListJoueur","Objet "+obj.getNom()+" equipé par "+res);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
