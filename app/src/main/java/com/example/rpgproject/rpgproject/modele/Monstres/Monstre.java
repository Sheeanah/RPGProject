package com.example.rpgproject.rpgproject.modele.Monstres;

import android.content.Context;

import com.example.rpgproject.rpgproject.modele.Objets.Objet;
import com.example.rpgproject.rpgproject.modele.Personnage;

import java.util.List;

/**
 * Created by Dorian on 28/01/2015.
 */
public abstract class Monstre extends Personnage {

    Context context;
    public Monstre(Context context){
        super();
        this.context=context;
    }

    @Override
    public int getNiveau(){
        return 0;
    }
    @Override
    public int getVie(){
        return 0;
    }
    @Override
    public int getAttaque(){
        return 0;
    }
    @Override
    public int getDefense(){
        return 0;
    }
    @Override
    public int getChance(){
        return 0;
    }
    @Override
    public String getNom(){
        return null;
    }

    public int getXp(){
        return 0;
    }
    public int getGolds(){
        return 0;
    }
    public List<Objet> getButin(){
        return null;
    }
}
