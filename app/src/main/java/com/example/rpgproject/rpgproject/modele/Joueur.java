package com.example.rpgproject.rpgproject.modele;

/**
 * Created by Dorian on 16/01/2015.
 */
public class Joueur extends Personnage {
    private int or;
    private int xp;

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }
}
