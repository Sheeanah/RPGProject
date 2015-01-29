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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        GestionnaireJoueur gestionnaire=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        final Joueur j = gestionnaire.getMainJoueur();

        FabriqueObjet fabrique = FabriqueObjet.getUniqueInstance();

        final Objet epee = fabrique.getObjet("Epee",getApplicationContext());
        Button btn_sword_price=(Button)findViewById(R.id.btn_sword_price);
        btn_sword_price.setText(epee.getPrixAchat()+ " or");
         btn_sword_price.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 buy(j, epee);
             }
         });
         TextView lbl_sword_name=(TextView)findViewById(R.id.lbl_sword_name);
         lbl_sword_name.setText(epee.getNom());
         TextView lbl_sword_stats=(TextView)findViewById(R.id.lbl_sword_stats);
         lbl_sword_stats.setText(epee.getBonusAtk());

         TextView lbl_money=(TextView)findViewById(R.id.lbl_money);
         lbl_money.setText(j.getOr());

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

    public void goToMap(){
           Intent mapIntent=new Intent(this,MainActivity.class);
            startActivity(mapIntent);
    }

    public void buy(Joueur j, Objet o)
    {
        if(j.acheter(o))
        {
            Toast.makeText(getApplicationContext(), "Achat effectué", Toast.LENGTH_SHORT).show();
            TextView lbl_money=(TextView)findViewById(R.id.lbl_money);
            lbl_money.setText(j.getOr());
        }
        else
        {
             Toast.makeText(getApplicationContext(), "Vous n'avez pas asssez d'or", Toast.LENGTH_SHORT).show();
        }
    }
}