package com.example.rpgproject.rpgproject.modele.Objets;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 28/01/2015.
 */
public class ArmureSimple extends Armure {
    ArmureSimple(Context context) {
        super(context);
    }

    @Override
    public int getBonusDef(){
        return 5;
    }

    @Override
    public int getBonusVie(){
        return 5;
    }

    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_objet_armure_simple);
    }

    @Override
    public int getPrixAchat(){
        return super.getPrixAchat();
    }
}
