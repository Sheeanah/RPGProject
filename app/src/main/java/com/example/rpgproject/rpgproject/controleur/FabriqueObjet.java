package com.example.rpgproject.rpgproject.controleur;

import android.content.Context;
import android.util.Log;

import com.example.rpgproject.rpgproject.modele.Objets.Objet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Dorian on 23/01/2015.
 */
public class FabriqueObjet {
    private static FabriqueObjet uniqueInstance;
    private static List<String> lstObjets;

    FabriqueObjet()
    {
        super();
        lstObjets=new ArrayList<String>();
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Objets.Epee");
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Objets.BouclierSimple");
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Objets.ArmureSimple");
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Objets.CasqueSimple");
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Objets.BijouVie");
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Objets.BijouChance");
    }

    public Objet getObjet(int objId,Context context) {
        Objet res=null;
        if(lstObjets.size()>objId)
        {
            try{
                Class newClass=Class.forName(lstObjets.get(objId));
                res=(Objet)newClass.getConstructor(Context.class).newInstance(context);
            }
            catch (Exception e){
                e.printStackTrace();
                Log.e("FabriqueObjet",e.getMessage());
            }
        }
        return res;
    }

    public Objet getObjet(String nomObj,Context context){
        Objet res=null;
        for(String str : lstObjets){
            String[] strSplit=str.split("\\.");
            if(strSplit[strSplit.length-1].equals(nomObj)){
                res=getObjet(lstObjets.indexOf(str),context);
            }
        }
        return res;
    }

    public int getIdObjet(String objClass){
        return lstObjets.indexOf(objClass);
    }

    public static FabriqueObjet getUniqueInstance()
    {
        if(uniqueInstance==null){
            uniqueInstance=new FabriqueObjet();
        }
        return uniqueInstance;
    }

    public HashMap<String,List<Objet>> getAllObjets(Context context){
        HashMap<String,List<Objet>> res=new HashMap<>();
        for(String strObj : lstObjets){
            Objet obj=getObjet(strObj,context);
            List<Objet> reslist=res.get(obj.getType());
            res.remove(obj.getType());
            if(reslist==null){
                reslist=new ArrayList<Objet>();
            }
            reslist.add(obj);
            res.put(obj.getType(),reslist);
        }
        return res;

    }
}
