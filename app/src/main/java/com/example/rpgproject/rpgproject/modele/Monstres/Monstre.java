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
    Monstre(Context context){
        super();
        this.context=context;
    }

    public abstract int getXp();
    public abstract int getGolds();
    public List<Objet> getButin(){
        return null;
    }
}
