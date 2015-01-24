package com.example.rpgproject.rpgproject.controleur;

import android.content.Context;
import android.util.Log;

import com.example.rpgproject.rpgproject.modele.Objet;
import java.util.ArrayList;
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
        lstObjets.add("com.example.rpgproject.rpgproject.modele.Epee");
        lstObjets.add("com.example.rpgproject.rpgproject.modele.BouclierSimple");
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
}
