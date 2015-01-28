package com.example.rpgproject.rpgproject.modele;

import android.content.Context;

/**
 * Created by Dorian on 28/01/2015.
 */
public abstract class Bijou extends Objet {

    Bijou(Context context){
        super(context);
    }

    @Override
    public int getBonusAtk() {
        return 0;
    }

    @Override
    public int getBonusDef() {
        return 0;
    }

    @Override
    public int getBonusChance() {
        return 0;
    }

    @Override
    public int getBonusVie() {
        return 0;
    }

    @Override
    public String getType() {
        return "Bijou";
    }
}
