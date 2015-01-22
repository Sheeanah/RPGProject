package com.example.rpgproject.rpgproject.modele;

/**
 * Created by Dorian on 16/01/2015.
 */
public class Joueur extends Personnage {


    private int or;
    private int xp;
    private int idjoueur;

    public Joueur(int idjoueur,int xp, int or) {
        this.xp = xp;
        this.or = or;
    }

    public int getIdjoueur() {
        return idjoueur;
    }

    public void setIdjoueur(int idjoueur) {
        this.idjoueur = idjoueur;
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
        return 1;
    }

    @Override
    public int getAttaque() {
        return 2;
    }

    @Override
    public int getDefense() {
        return 1;
    }

    @Override
    public float getChance() {
        return 0;
    }
}
