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

        //initialisation du joueur principal
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        mainJoueur=gestionnaireJoueur.getMainJoueur();

        //ajout des fonctions permettant de changer de vue
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

        //fonction initialisant le listener pour la localisation
        processLocation();
    }

    public void processLocation(){
        //on récupère le service de localisation de l'appareil
        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //dans le cas où le service n'est pas actif, on retourne un message d'information
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(getApplicationContext(),"GPS non activé, impossible de mettre à jour les coordonnées.", Toast.LENGTH_SHORT).show();
        }
        else{
            //on créé le listner
            myListener = new LocationListener() {
                //fonction appelée lorsque l'appareil détecte un changement de position
                public void onLocationChanged(Location location) {
                    //on va récupérer les coordonnées stockées dans les préférences
                    SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
                    //dans le cas où elles n'existent pas encore, on les initialise à 0
                    double lastX=prefs.getFloat("x",0);
                    double lastY=prefs.getFloat("y",0);

                    //on récupère l'éditeur pour permettre de rentrer les nouvelles valeurs après le traitement
                    SharedPreferences.Editor prefEdit=prefs.edit();

                    //on vérifié que la localisation reçue est correcte
                    if(location!=null){
                        //tableau contant les résultat de la fonction suivante
                        float results[]=new float[10];

                        //fonction remplissant le tableau reçu en paramètre, la distance en mètres entre les 2 points se trouvant dans la première case
                        Location.distanceBetween(lastX,lastY,location.getLatitude(),location.getLongitude(),results);

                        //on ajoute l'or au joueur en fonction de la distance, puis on sauvegarde le joueur dans la base
                        mainJoueur.addGold((int) results[0]);
                        gestionnaireJoueur.saveJoueur(mainJoueur.getId());

                        //message d'information indiquant la distance
                        Toast.makeText(getApplicationContext(),"Distance parccourue : " + Float.toString(results[0]) + "m.", Toast.LENGTH_SHORT).show();

                        //on met a jour les données stockées dans les préférences
                        prefEdit.putFloat("x",(float)location.getLatitude());
                        prefEdit.putFloat("y",(float)location.getLongitude());
                        prefEdit.commit();
                    }
                    else{
                        //message d'information envoyé dans le cas où la localisation reçue n'est pas valable
                        Toast.makeText(getApplicationContext(),"GPS activé, mais pas de coordonnées disponibles.", Toast.LENGTH_SHORT).show();
                    }
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {}
                public void onProviderEnabled(String provider) {}
                public void onProviderDisabled(String provider) {}
            };

            //on ajoute le listener dans la liste des objets à notifier lors d'un changement de position
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

    //fonctions appelées par les boutons
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
    //fonction appelée lorsque l'activité passe en background
    public void onPause(){
        super.onPause();
        //sauvegarde du joueur
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
        //on annule la réception des changements de position
        locationManager.removeUpdates(myListener);
    }
}
