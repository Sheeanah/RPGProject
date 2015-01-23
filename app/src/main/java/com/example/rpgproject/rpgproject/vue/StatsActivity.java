package com.example.rpgproject.rpgproject.vue;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.database.RPGProjectDB;
import com.example.rpgproject.rpgproject.modele.Joueur;
import com.example.rpgproject.rpgproject.modele.Objet;

public class StatsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Joueur j= RPGProjectDB.getJoueur(getApplicationContext(),1);
        if(j!=null){
            FabriqueObjet fabrique=FabriqueObjet.getUniqueInstance();
            Objet obj=fabrique.getObjet(0, getApplicationContext());
            if(obj!=null){
                j.equiper(obj);
            }
            obj=fabrique.getObjet(1,getApplicationContext());
            if(obj!=null){
                j.equiper(obj);
            }


            TextView txt_stats_life=(TextView)findViewById(R.id.lbl_stats_life);
            txt_stats_life.setText(txt_stats_life.getText().toString()+" "+j.getVie());
            TextView txt_stats_atk=(TextView)findViewById(R.id.lbl_stats_atk);
            txt_stats_atk.setText(txt_stats_atk.getText().toString()+" "+j.getAttaque());
            TextView txt_stats_def=(TextView)findViewById(R.id.lbl_stats_def);
            txt_stats_def.setText(txt_stats_def.getText().toString()+" "+j.getDefense());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
