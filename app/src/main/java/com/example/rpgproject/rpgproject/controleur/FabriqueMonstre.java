package com.example.rpgproject.rpgproject.controleur;

import android.content.Context;
import android.util.Log;

import com.example.rpgproject.rpgproject.modele.Monstres.Monstre;
import com.example.rpgproject.rpgproject.modele.Objets.Objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dorian on 28/01/2015.
 */
public class FabriqueMonstre{

    private static FabriqueMonstre uniqueInstance;
    private static List<String> lstMonstres;

    FabriqueMonstre()
    {
        super();
        lstMonstres =new ArrayList<String>();
        lstMonstres.add("com.example.rpgproject.rpgproject.modele.Monstres.Gobelin");
        lstMonstres.add("com.example.rpgproject.rpgproject.modele.Monstres.Orc");
    }

    public Monstre getMonstre(int monstreId,Context context) {
        Monstre res=null;
        if(lstMonstres.size()>monstreId)
        {
            try{
                Class newClass=Class.forName(lstMonstres.get(monstreId));
                res=(Monstre)newClass.getConstructor(Context.class).newInstance(context);
            }
            catch (Exception e){
                e.printStackTrace();
                Log.e("FabriqueObjet", e.getMessage());
            }
        }
        return res;
    }

    public Monstre getMonstre(String nomMonstre,Context context){
        Monstre res=null;
        for(String str : lstMonstres){
            String[] strSplit=str.split(".");
            if(strSplit[strSplit.length-1].equals(nomMonstre)){
                res=getMonstre(lstMonstres.indexOf(str),context);
            }
        }
        return res;
    }

    public int getIdObjet(String monstreClass){
        return lstMonstres.indexOf(monstreClass);
    }

    public static FabriqueMonstre getUniqueInstance()
    {
        if(uniqueInstance==null){
            uniqueInstance=new FabriqueMonstre();
        }
        return uniqueInstance;
    }

    public Monstre getObjet(String nomMonstre,Context context){
        Monstre res=null;
        for(String str : lstMonstres){
            String[] strSplit=str.split("\\.");
            if(strSplit[strSplit.length-1].equals(nomMonstre)){
                res=getMonstre(lstMonstres.indexOf(str), context);
            }
        }
        return res;
    }

    public List<Monstre> getMonstres(Context context){
        int i=0;
        List<Monstre> res=new ArrayList<Monstre>();
        while(i<lstMonstres.size()){
            res.add(getMonstre(i,context));
            i++;
        }
        return res;
    }
}
