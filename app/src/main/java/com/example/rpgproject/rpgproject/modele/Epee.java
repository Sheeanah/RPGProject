package com.example.rpgproject.rpgproject.modele;


import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 23/01/2015.
 */
public class Epee extends Arme {

    public Epee(Context context)
    {
        super(context);
    }

    @Override
    public int getBonusAtk() {
        return 10;
    }
    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_objet_epee_simple);
    }
}
