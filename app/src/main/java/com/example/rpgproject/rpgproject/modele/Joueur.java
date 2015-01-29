package com.example.rpgproject.rpgproject.modele;

import android.content.Context;

import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
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
    private int id;
    private String nom;
    private GestionnaireJoueur gestionnaire;

    public Joueur(int id,int xp, int or,String nom,Context context) {
        this.id = id;
        this.xp = xp;
        this.or = or;
        this.nom=nom;
        this.inventaire=new ArrayList<Objet>();
        this.gestionnaire=GestionnaireJoueur.getUniqueInstance(context);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
        gestionnaire.saveJoueur(this.id);
    }

    public int getXp() {
        return xp;
    }

    private void setXp(int xp) {
        this.xp = xp;
        gestionnaire.saveJoueur(this.id);
    }

    public int getOr() {
        return or;
    }

    private void setOr(int or) {
        this.or = or;
        gestionnaire.saveJoueur(this.id);
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

    public void addGold(int or){
        this.or+=or;
        gestionnaire.saveJoueur(this.id);
    }

    private boolean removeGold(int or){
        boolean res=false;
        if(this.or-or>0){
            this.or-=or;
            res=true;
        }
        return res;
    }

    public void addXp(int xp){
        this.xp+=xp;
    }

    public boolean acheter(Objet obj){
        boolean res=false;
        if(removeGold(obj.getPrixAchat())){
            equiper(obj);
            gestionnaire.saveJoueur(this.id);
            res=true;
        }
        return res;
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
    public int getChance() {
        return 50;
    }

    @Override
    public String toString()
    {
        return "ID : "+ id +" Nom : "+nom+" Or : "+or+" XP : "+xp;
    }

    @Override
    public String getNom(){
        return "'"+this.nom+"'";
    }
}
