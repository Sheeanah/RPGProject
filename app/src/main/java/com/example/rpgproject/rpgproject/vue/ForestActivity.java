package com.example.rpgproject.rpgproject.vue;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rpgproject.rpgproject.R;
import com.example.rpgproject.rpgproject.controleur.GestionnaireJoueur;
import com.example.rpgproject.rpgproject.controleur.FabriqueMonstre;
import com.example.rpgproject.rpgproject.modele.Joueur;
import com.example.rpgproject.rpgproject.modele.Monstres.Monstre;

import java.util.List;
import java.util.Random;


public class ForestActivity extends ActionBarActivity {

    private GestionnaireJoueur gestionnaireJoueur;
    private Joueur mainJoueur;
    private FabriqueMonstre fabriqueMonstre;
    private Monstre monstre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        mainJoueur=gestionnaireJoueur.getMainJoueur();

        TextView str_stats_lvl_hero=(TextView)findViewById(R.id.str_stats_lvl_hero);
        str_stats_lvl_hero.setText(str_stats_lvl_hero.getText().toString() + " " + mainJoueur.getNiveau());

        TextView life_to_update_hero=(TextView)findViewById(R.id.life_to_update_hero);
        life_to_update_hero.setText(mainJoueur.getVie());

        TextView life_fix_hero=(TextView)findViewById(R.id.life_fix_hero);
        life_fix_hero.setText(mainJoueur.getVie());

        TextView str_stats_atk_hero=(TextView)findViewById(R.id.str_stats_atk_hero);
        str_stats_atk_hero.setText(str_stats_atk_hero.getText().toString() + " " + mainJoueur.getAttaque());

        TextView str_stats_def_hero=(TextView)findViewById(R.id.str_stats_def_hero);
        str_stats_def_hero.setText(str_stats_def_hero.getText().toString()+" "+mainJoueur.getDefense());

        TextView str_stats_luck_hero=(TextView)findViewById(R.id.str_stats_luck_hero);
        str_stats_luck_hero.setText(str_stats_luck_hero.getText().toString()+" "+mainJoueur.getChance());

        fabriqueMonstre=FabriqueMonstre.getUniqueInstance();
        Monstre monster = random_monster();

        TextView str_stats_lvl_monster=(TextView)findViewById(R.id.str_stats_lvl_monster);
        str_stats_lvl_monster.setText(str_stats_lvl_monster.getText().toString() + " " + monster.getNiveau());

        TextView life_to_update_monster=(TextView)findViewById(R.id.life_to_update_monster);
        life_to_update_monster.setText(monster.getVie());

        TextView life_fix_monster=(TextView)findViewById(R.id.life_fix_monster);
        life_fix_monster.setText(monster.getVie());

        TextView str_stats_atk_monster=(TextView)findViewById(R.id.str_stats_atk_monster);
        str_stats_atk_monster.setText(str_stats_atk_monster.getText().toString() + " " + monster.getAttaque());

        TextView str_stats_def_monster=(TextView)findViewById(R.id.str_stats_def_monster);
        str_stats_def_monster.setText(str_stats_def_monster.getText().toString()+" "+monster.getDefense());

        TextView str_stats_luck_monster=(TextView)findViewById(R.id.str_stats_luck_monster);
        str_stats_luck_monster.setText(str_stats_luck_monster.getText().toString()+" "+monster.getChance());

        Button btn_atk=(Button)findViewById(R.id.btn_atk);
        btn_atk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atk();
            }
        });

        Button btn_def=(Button)findViewById(R.id.btn_def);
        btn_def.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                def();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forest, menu);
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

    public void onPause(){
        super.onPause();
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
    }

    public int Random(int min, int max)
    {
        max++;
        return (int)(Math.random() * (max-min)) + min;
    }

    public boolean miss(double luck)
    {
        boolean is_missed = true;

        int random = Random(0, (int)luck);
        if(random < luck)
        {
            is_missed = false;
        }

        return is_missed;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public Monstre random_monster()
    {
        List<Monstre> listMonstres = fabriqueMonstre.getMonstres(getApplicationContext());
        int sizeList = listMonstres.size();
        int monster_index = Random(0, sizeList-1);

        return fabriqueMonstre.getMonstre(monster_index, getApplicationContext());
    }

    public int damages(int life, int atk, int def)
    {
        return life - atk + def;
    }

    public void atk()
    {

    }

    public void def()
    {

    }
}
