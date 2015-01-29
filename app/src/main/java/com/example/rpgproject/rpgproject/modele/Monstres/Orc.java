package com.example.rpgproject.rpgproject.modele.Monstres;

import android.content.Context;

import com.example.rpgproject.rpgproject.R;

/**
 * Created by Dorian on 28/01/2015.
 */
public class Orc extends Monstre{
    Orc(int niveau,Context context){
        super(context);
    }

    @Override
    public int getXp() {
        return 10*getNiveau();
    }

    @Override
    public int getGolds() {
        return 5*getNiveau();
    }

    @Override
    public int getNiveau() {
        return 2;
    }

    @Override
    public int getVie() {
        return 10*getNiveau();
    }

    @Override
    public int getAttaque() {
        return 2*getNiveau();
    }

    @Override
    public int getDefense() {
        return 2*getNiveau();
    }

    @Override
    public int getChance() {
        return 10;
    }

    @Override
    public String getNom() {
        return context.getResources().getString(R.string.str_monstre_orc);
    }
}
