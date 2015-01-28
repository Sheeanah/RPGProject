package com.example.rpgproject.rpgproject.modele.Objets;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 23/01/2015.
 */
public class BouclierSimple extends Bouclier {

    public BouclierSimple(Context context){
        super(context);
    }

    @Override
    public int getBonusDef() {
        return 10;
    }

    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_objet_bouclier_simple);
    }

    @Override
    public int getPrixAchat(){
        return super.getPrixAchat();
    }
}
