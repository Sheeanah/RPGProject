package com.example.rpgproject.rpgproject.vue;
import android.widget.Toast;
import java.util.Random;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
import com.example.rpgproject.rpgproject.modele.Joueur;


public class MineActivity extends ActionBarActivity {

    private Joueur mainJoueur;
    private GestionnaireJoueur gestionnaireJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        // On instancie le gestionnaireJoueur et le joueur principal
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        mainJoueur=gestionnaireJoueur.getMainJoueur();

        // Création de l'action clic pour retourner vers la carte
        Button btn_map=(Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMap();
            }
        });

        // Création de l'action clic pour fouiller
        Button btn_excavation=(Button)findViewById(R.id.btn_excavation);
        btn_excavation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doExcavations(mainJoueur);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mine, menu);
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
        this.finish();
    }

    // Fonction de fouille
    public void doExcavations(Joueur j){
        // On récupère aléatoirement un boolean pour savoir si le joueur trouve de l'or
        boolean isFound = getRandomBoolean();

        // Si oui
        if(isFound)
        {
            // On récupère le montant d'or gagné
            int money = getMoney(j.getChance());
            // On l'affiche
            String text = "Vous gagnez " + money + " or";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            // On l'ajoute au joueur
            j.addGold(money);
        }
    }

    // Renvoi true ou false aléatoirement
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    // Renvoi l'argent que peut gagner le joueur en cliquant
    public int getMoney(int luck)
    {
        return luck * 100;
    }

    // En pause, le jeu sauvegarde le joueur dans la base de donnée
    public void onPause(){
        super.onPause();
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
    }

}
