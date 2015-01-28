package com.example.rpgproject.rpgproject.modele;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 28/01/2015.
 */
public class CasqueSimple extends Casque {
    CasqueSimple(Context context) {
        super(context);
    }

    @Override
    public int getBonusDef(){
        return 1;
    }

    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_objet_casque_simple);
    }
}
