package com.example.rpgproject.rpgproject.modele;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 23/01/2015.
 */
public abstract class Bouclier extends Objet{

    Bouclier(Context context){
        super(context);
    }

    @Override
    public int getBonusAtk() {
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
    public String getType(){
        return "Bouclier";
    }


}
