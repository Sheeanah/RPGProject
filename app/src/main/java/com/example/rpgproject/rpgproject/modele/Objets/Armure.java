package com.example.rpgproject.rpgproject.modele.Objets;

import android.content.Context;

/**
 * Created by Dorian on 28/01/2015.
 */
public abstract class Armure extends Objet {

    Armure(Context context) {
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
    public String getType() {
        return "Armure";
    }

    @Override
    public int getPrixAchat(){
        return super.getPrixAchat()*5;
    }
}
