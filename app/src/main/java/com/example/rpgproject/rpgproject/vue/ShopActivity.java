package com.example.rpgproject.rpgproject.vue;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
import com.example.rpgproject.rpgproject.modele.Joueur;
import com.example.rpgproject.rpgproject.modele.Objets.Objet;


public class ShopActivity extends ActionBarActivity {
    private GestionnaireJoueur gestionnaireJoueur;
    private Joueur mainJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // On instancie le gestionnaireJoueur et le joueur principal
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        mainJoueur = gestionnaireJoueur.getMainJoueur();

        // On instancie la fabriqueobjet
        FabriqueObjet fabrique = FabriqueObjet.getUniqueInstance();

        // On recupère l'objet epee et on affiche son price, son nom, et les statistiques qu'elle augmente
        final Objet sword = fabrique.getObjet("Epee",getApplicationContext());
        Button btn_sword_price=(Button)findViewById(R.id.btn_sword_price);
        btn_sword_price.setText(sword.getPrixAchat() + " or");
        btn_sword_price.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             buy(mainJoueur, sword);
         }
        });
        TextView lbl_sword_name=(TextView)findViewById(R.id.lbl_sword_name);
        lbl_sword_name.setText(sword.getNom());
        TextView lbl_sword_stats=(TextView)findViewById(R.id.lbl_sword_stats);
        lbl_sword_stats.setText(" (Atk : +" + sword.getBonusAtk() + ")");

        // Idem pour l'objet bouclier simple
        final Objet shiel = fabrique.getObjet("BouclierSimple",getApplicationContext());
        Button btn_shield_price=(Button)findViewById(R.id.btn_shield_price);
        btn_shield_price.setText(shiel.getPrixAchat() + " or");
        btn_shield_price.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              buy(mainJoueur, shiel);
          }
        });
        TextView lbl_shield_name=(TextView)findViewById(R.id.lbl_shield_name);
        lbl_shield_name.setText(shiel.getNom());
        TextView lbl_shield_stats=(TextView)findViewById(R.id.lbl_shield_stats);
        lbl_shield_stats.setText(" (Def: +" + shiel.getBonusDef() + ")");

        // Idem pour l'objet armure simple
        final Objet armor = fabrique.getObjet("ArmureSimple",getApplicationContext());
        Button btn_armor_price=(Button)findViewById(R.id.btn_armor_price);
        btn_armor_price.setText(armor.getPrixAchat() + " or");
        btn_armor_price.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               buy(mainJoueur, armor);
           }
        });
        TextView lbl_armor_name=(TextView)findViewById(R.id.lbl_armor_name);
        lbl_armor_name.setText(armor.getNom());
        TextView lbl_armor_stats=(TextView)findViewById(R.id.lbl_armor_stats);
        lbl_armor_stats.setText(" (Atk: +" + armor.getBonusAtk() + ", Def: +" + armor.getBonusDef() + ")");

        // Idem pour l'objet casque simple
        final Objet helmet = fabrique.getObjet("CasqueSimple",getApplicationContext());
        Button btn_helmet_price=(Button)findViewById(R.id.btn_helmet_price);
        btn_helmet_price.setText(helmet.getPrixAchat() + " or");
        btn_helmet_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy(mainJoueur, helmet);
            }
        });
        TextView lbl_helmet_name=(TextView)findViewById(R.id.lbl_helmet_name);
        lbl_helmet_name.setText(helmet.getNom());
        TextView lbl_helmet_stats=(TextView)findViewById(R.id.lbl_helmet_stats);
        lbl_helmet_stats.setText(" (Def: +" + helmet.getBonusDef() + ")");

        // Idem pour l'objet bijoux de vie
        final Objet life = fabrique.getObjet("BijouVie",getApplicationContext());
        Button btn_life_price=(Button)findViewById(R.id.btn_life_price);
        btn_life_price.setText(life.getPrixAchat() + " or");
        btn_life_price.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             buy(mainJoueur, life);
         }
        });
        TextView lbl_life_name=(TextView)findViewById(R.id.lbl_life_name);
        lbl_life_name.setText(life.getNom());
        TextView lbl_life_stats=(TextView)findViewById(R.id.lbl_life_stats);
        lbl_life_stats.setText(" (Vie: +" + life.getBonusAtk() + ")");

        // Idem pour l'objet bijoux de chance
        final Objet luck = fabrique.getObjet("BijouChance",getApplicationContext());
        Button btn_luck_price=(Button)findViewById(R.id.btn_luck_price);
        btn_luck_price.setText(luck.getPrixAchat() + " or");
        btn_luck_price.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              buy(mainJoueur, luck);
          }
        });
        TextView lbl_luck_name=(TextView)findViewById(R.id.lbl_luck_name);
        lbl_luck_name.setText(luck.getNom());
        TextView lbl_luck_stats=(TextView)findViewById(R.id.lbl_luck_stats);
        lbl_luck_stats.setText(" (Chance: +" + luck.getBonusChance() + ")");

        // Affiche l'or du joueur
        TextView lbl_money=(TextView)findViewById(R.id.lbl_money);
        lbl_money.setText(mainJoueur.getOr() + " or");

        // Création de l'action clic pour retourner vers la carte
        Button btn_map=(Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 goToMap();
             }
         });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
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

    // Fonction d'achat
    public void buy(Joueur j, Objet o)
    {
        // Si l'achat fonctionne
        if(j.acheter(o))
        {
            // On l'afffiche et on affiche son or modifié
            Toast.makeText(getApplicationContext(), "Achat effectué", Toast.LENGTH_SHORT).show();
            TextView lbl_money=(TextView)findViewById(R.id.lbl_money);
            lbl_money.setText(j.getOr() + " or");
        }
        else
        {
            // Sinon on lui dit
             Toast.makeText(getApplicationContext(), "Achat impossible!", Toast.LENGTH_SHORT).show();
        }
    }

    // En pause, le jeu sauvegarde le joueur dans la base de donnée
    @Override
    public void onPause(){
        super.onPause();
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
    }
}