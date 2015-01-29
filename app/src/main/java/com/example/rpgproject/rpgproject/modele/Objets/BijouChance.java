package com.example.rpgproject.rpgproject.modele.Objets;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 28/01/2015.
 */
public class BijouChance extends Bijou{
    public BijouChance(Context context) {
        super(context);
    }

    @Override
    public int getBonusChance(){
        return 10;
    }

    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_objet_bijou_chance);
    }

    @Override
    public int getPrixAchat(){
        return super.getPrixAchat();
    }
}
