package com.example.rpgproject.rpgproject.modele.Objets;

import android.content.Context;

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

    @Override
    public int getPrixAchat(){
        return super.getPrixAchat()*3;
    }


}
