package com.example.rpgproject.rpgproject.vue;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.FabriqueObjet;
import com.example.rpgproject.rpgproject.modele.Objet;


public class ShopActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //FabriqueObjet fabrique = FabriqueObjet.getUniqueInstance();
        //Objet objet = fabrique.getObjet(1,getApplicationContext());
        //String atk = "" + objet.getBonusAtk();

        //Toast.makeText(getApplicationContext(), atk, Toast.LENGTH_SHORT).show();
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
}
