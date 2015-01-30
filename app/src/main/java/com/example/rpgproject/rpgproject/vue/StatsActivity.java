package com.example.rpgproject.rpgproject.vue;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
import com.example.rpgproject.rpgproject.modele.Joueur;
import com.example.rpgproject.rpgproject.modele.Objets.Objet;

public class StatsActivity extends ActionBarActivity {

    private Joueur mainJoueur;
    private GestionnaireJoueur gestionnaireJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Création de l'action clic pour retourner vers la carte
        Button btn_map=(Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMap();
            }
        });

        // On instancie le gestionnaireJoueur
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());

        // On instancie le joueur, et s'il est trouvé
        mainJoueur= gestionnaireJoueur.getMainJoueur();
        if(mainJoueur!=null){

            // On affiche son niveau
            TextView txt_stats_lvl=(TextView)findViewById(R.id.lbl_stats_lvl);
            txt_stats_lvl.setText(txt_stats_lvl.getText().toString() + " " + mainJoueur.getNiveau());

            // On affiche son experience
            TextView txt_stats_xp=(TextView)findViewById(R.id.lbl_stats_xp);
            txt_stats_xp.setText(txt_stats_xp.getText().toString()+" "+mainJoueur.getXp());

            // On affiche sa vie
            TextView txt_stats_life=(TextView)findViewById(R.id.lbl_stats_life);
            txt_stats_life.setText(txt_stats_life.getText().toString()+" "+mainJoueur.getVie());

            // On affiche son attaque
            TextView txt_stats_atk=(TextView)findViewById(R.id.lbl_stats_atk);
            txt_stats_atk.setText(txt_stats_atk.getText().toString() + " " + mainJoueur.getAttaque());

            // On affiche sa defense
            TextView txt_stats_def=(TextView)findViewById(R.id.lbl_stats_def);
            txt_stats_def.setText(txt_stats_def.getText().toString()+" "+mainJoueur.getDefense());

            // On affiche sa chance
            TextView txt_stats_luck=(TextView)findViewById(R.id.lbl_stats_luck);
            txt_stats_luck.setText(txt_stats_luck.getText().toString()+" "+mainJoueur.getChance());

            // On affiche son or
            TextView txt_stats_gold=(TextView)findViewById(R.id.lbl_stats_gold);
            txt_stats_gold.setText(txt_stats_gold.getText().toString() + " " + mainJoueur.getOr());

            // On affiche son inventaire
            LinearLayout layout=(LinearLayout)findViewById(R.id.container_inventory_items);
            for(Objet obj : mainJoueur.getInventaire()){
                TextView tv=new TextView(getApplicationContext());
                tv.setText(obj.getNom());
                layout.addView(tv);
            }
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

    // Redirection vers la carte
    public void goToMap(){
        Intent mapIntent=new Intent(this,MainActivity.class);
        startActivity(mapIntent);
    }

    // En pause, le jeu sauvegarde le joueur dans la base de donnée
    @Override
    public void onPause(){
        super.onPause();
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
    }
}
