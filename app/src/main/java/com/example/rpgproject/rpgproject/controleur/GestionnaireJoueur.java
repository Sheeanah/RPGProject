package com.example.rpgproject.rpgproject.controleur;

import android.content.Context;
import android.util.Log;

import com.example.rpgproject.rpgproject.database.RPGProjectDB;
import com.example.rpgproject.rpgproject.modele.Joueur;

import java.util.List;

/**
 * Created by Dorian on 23/01/2015.
 */
public class GestionnaireJoueur {
    private static GestionnaireJoueur uniqueInstance;
    private List<Joueur> listeJoueurs;
    private static Context context;

    private GestionnaireJoueur(Context context){
        super();
        GestionnaireJoueur.context=context;
        listeJoueurs= RPGProjectDB.getListJoueurs(context);
    }

    public void addJoueur(int id, int or, int xp,String nom){
        if(getJoueur(id)==null){
            Joueur j =new Joueur(id,xp,or,nom,context);
            RPGProjectDB.addJoueur(GestionnaireJoueur.context,j);
            listeJoueurs.add(RPGProjectDB.getJoueur(GestionnaireJoueur.context,id));
        }
        else{
            Log.e("addJoueur","Id du joueur ("+id+") déjà utilisée.");
        }
    }
    public Joueur getJoueur(int joueurId){
        Joueur res=null;
        for(Joueur j : listeJoueurs){
            if(j.getId()==joueurId){
                res=j;
            }
        }
        return res;
    }
    public void saveJoueur(int id){
        Joueur j=getJoueur(id);
        if(j!=null){
            RPGProjectDB.saveJoueur(GestionnaireJoueur.context,j);
        }
    }
    public Joueur getMainJoueur(){
        return getJoueur(1);
    }

    public void refresh(Context context){
        GestionnaireJoueur.context=context;
        listeJoueurs=RPGProjectDB.getListJoueurs(context);
    }

    public static GestionnaireJoueur getUniqueInstance(Context context){
        if(uniqueInstance==null){
            uniqueInstance=new GestionnaireJoueur(context);
        }
        GestionnaireJoueur.context=context;
        return uniqueInstance;
    }
}
