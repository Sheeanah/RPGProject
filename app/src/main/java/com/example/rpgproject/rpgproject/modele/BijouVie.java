package com.example.rpgproject.rpgproject.modele;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 28/01/2015.
 */
public class BijouVie extends Bijou {
    BijouVie(Context context) {
        super(context);
    }

    @Override
    public int getBonusVie(){
        return 5;
    }

    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_objet_bijou_vie);
    }
}
