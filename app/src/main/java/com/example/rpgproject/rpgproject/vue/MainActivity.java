package com.example.rpgproject.rpgproject.vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
import com.example.rpgproject.rpgproject.modele.Joueur;


public class MainActivity extends ActionBarActivity {

    private LocationManager locationManager;
    private LocationListener myListener;
    private Joueur mainJoueur;
    private GestionnaireJoueur gestionnaireJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        mainJoueur=gestionnaireJoueur.getMainJoueur();

        ImageView img_mine=(ImageView)findViewById(R.id.img_mine);
        img_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMine();
            }
        });

        ImageView img_forest=(ImageView)findViewById(R.id.img_forest);
        img_forest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToForest();
            }
        });

        ImageView img_shop=(ImageView)findViewById(R.id.img_shop);
        img_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShop();
            }
        });

        ImageView img_stats=(ImageView)findViewById(R.id.img_stats);
        img_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToStats();
            }
        });

        processLocation();
    }

    public void processLocation(){
        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(getApplicationContext(),"GPS non activé, impossible de mettre à jour les coordonnées.", Toast.LENGTH_SHORT).show();
        }
        else{
            // Define a listener that responds to location updates
            myListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
                    double lastX=prefs.getFloat("x",0);
                    double lastY=prefs.getFloat("y",0);
                    SharedPreferences.Editor prefEdit=prefs.edit();

                    if(location!=null){
                        float results[]=new float[10];
                        Location.distanceBetween(lastX,lastY,location.getLatitude(),location.getLongitude(),results);
                        mainJoueur.addGold((int) results[0]/1000);
                        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
                        Toast.makeText(getApplicationContext(),"Distance parccourue : " + Float.toString(results[0]) + "m. Distance/1000 or ajouté.", Toast.LENGTH_SHORT).show();
                        prefEdit.putFloat("x",(float)location.getLatitude());
                        prefEdit.putFloat("y",(float)location.getLongitude());
                        prefEdit.commit();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"GPS activé, mais pas de coordonnées disponibles.", Toast.LENGTH_SHORT).show();
                    }
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {}
                public void onProviderEnabled(String provider) {}
                public void onProviderDisabled(String provider) {}
            };

            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myListener);
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
    public void onPause(){
        super.onPause();
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
        locationManager.removeUpdates(myListener);
    }
}
