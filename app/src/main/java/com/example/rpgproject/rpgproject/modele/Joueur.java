package com.example.rpgproject.rpgproject.modele;

/**
 * Created by Dorian on 16/01/2015.
 */
public class Joueur extends Personnage {


    private int or;
    private int xp;

    public Joueur(int xp, int or) {
        this.xp = xp;
        this.or = or;
    }

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

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getVie() {
        return 0;
    }

    @Override
    public int getAttaque() {
        return 0;
    }

    @Override
    public int getDefense() {
        return 0;
    }

    @Override
    public float getChance() {
        return 0;
    }
}
