package com.example.rpgproject.rpgproject.modele;

import com.example.rpgproject.rpgproject.modele.Objets.Objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 16/01/2015.
 */
public class Joueur extends Personnage {

    private List<Objet> inventaire;
    private int or;
    private int xp;
    private int idjoueur;
    private String nom;

    public Joueur(int idjoueur,int xp, int or,String nom) {
        this.idjoueur=idjoueur;
        this.xp = xp;
        this.or = or;
        this.nom=nom;
        this.inventaire=new ArrayList<Objet>();
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

    public List<Objet> getInventaire(){
        return this.inventaire;
    }

    public void equiper(Objet obj){
        this.inventaire.add(obj);
    }

    public int getNiveau() {
        return (int)xp/1000;
    }

    @Override
    public int getVie() {
        int res=0;
        if(inventaire.size()>0){
            for(Objet obj : inventaire){
                res+=obj.getBonusVie();
            }
        }
        return res+1;
    }

    @Override
    public int getAttaque() {
        int res=0;
        if(inventaire.size()>0){
            for(Objet obj : inventaire){
                res+=obj.getBonusAtk();
            }
        }
        return res+2;
    }

    @Override
    public int getDefense() {
        int res=0;
        if(inventaire.size()>0){
            for(Objet obj : inventaire){
                res+=obj.getBonusDef();
            }
        }
        return res+1;
    }

    @Override
    public float getChance() {
        return 0;
    }

    @Override
    public String toString()
    {
        return "ID : "+idjoueur+" Nom : "+nom+" Or : "+or+" XP : "+xp;
    }

    @Override
    public String getNom(){
        return "'"+this.nom+"'";
    }
}
