package com.example.rpgproject.rpgproject.modele;

import android.content.Context;

/**
 * Created by Dorian on 22/01/2015.
 */
public abstract class Objet {
    Context context;

    Objet(Context context){
        super();
        this.context=context;
    }
    public abstract int getBonusAtk();
    public abstract int getBonusDef();
    public abstract int getBonusChance();
    public abstract int getBonusVie();
    public abstract String getType();
    public abstract String getNom();
}
