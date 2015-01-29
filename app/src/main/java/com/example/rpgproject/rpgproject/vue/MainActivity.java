package com.example.rpgproject.rpgproject.vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
import com.example.rpgproject.rpgproject.modele.Joueur;


public class MainActivity extends ActionBarActivity {

    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GestionnaireJoueur gestionnaire=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        Joueur mainPlayer=gestionnaire.getMainJoueur();

        Button btn_mine=(Button)findViewById(R.id.btn_mine);
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMine();
            }
        });

        Button btn_forest=(Button)findViewById(R.id.btn_forest);
        btn_forest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToForest();
            }
        });

        Button btn_shop=(Button)findViewById(R.id.btn_shop);
        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShop();
            }
        });

        Button btn_stats=(Button)findViewById(R.id.btn_stats);
        btn_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStats();
            }
        });

        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(getApplicationContext(),"GPS non activé, impossible de mettre à jour les coordonnées.", Toast.LENGTH_SHORT).show();
        }
        else{
            SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
            double lastX=prefs.getFloat("x",0);
            double lastY=prefs.getFloat("y",0);
            SharedPreferences.Editor prefEdit=prefs.edit();
            provider=locationManager.getBestProvider(new Criteria(),false);
            Location loc=locationManager.getLastKnownLocation(provider);

            if(loc!=null){
                float results[]=new float[0];
                Location.distanceBetween(lastX,lastY,loc.getLatitude(),loc.getLongitude(),results);
                mainPlayer.addGold((int) results[0]);
                gestionnaire.saveJoueur(mainPlayer.getId());
                Toast.makeText(getApplicationContext(), Float.toString(results[0]), Toast.LENGTH_SHORT).show();

            }
            Toast.makeText(getApplicationContext(),"GPS activé, mais pas de coordonnées disponibles.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToMine(){
        Intent mineIntent=new Intent(this,MineActivity.class);
        startActivity(mineIntent);
    }
    public void goToForest(){
        Intent mineIntent=new Intent(this,ForestActivity.class);
        startActivity(mineIntent);
    }
    public void goToShop(){
        Intent mineIntent=new Intent(this,ShopActivity.class);
        startActivity(mineIntent);
    }
    public void goToArena(){
        Intent mineIntent=new Intent(this,ArenaActivity.class);
        startActivity(mineIntent);
    }
    public void goToStats(){
        Intent statsIntent=new Intent(this,StatsActivity.class);
        startActivity(statsIntent);
    }
}
