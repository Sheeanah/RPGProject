package com.example.rpgproject.rpgproject.vue;

import android.content.Context;
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

    private int Life_monster;
    private int Life_hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forest);

        // On instancie le gestionnaireJoueur et le joueur principal
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        mainJoueur=gestionnaireJoueur.getMainJoueur();

        // On affiche le niveau du joueur
        TextView str_stats_lvl_hero=(TextView)findViewById(R.id.str_stats_lvl_hero);
        str_stats_lvl_hero.setText(str_stats_lvl_hero.getText().toString() + " " + mainJoueur.getNiveau());

        //On met la vie du joueur dans la variable intermédiaire
        Life_hero = mainJoueur.getVie();

        // On affiche la vie du joueur (qui sera modifiée)
        final TextView life_to_update_hero=(TextView)findViewById(R.id.life_to_update_hero);
        life_to_update_hero.setText(mainJoueur.getVie() + "");

        // On affiche la vie du joueur (fixe)
        TextView life_fix_hero=(TextView)findViewById(R.id.life_fix_hero);
        life_fix_hero.setText(mainJoueur.getVie() + "");

        // On affiche l'attaque du joueur
        TextView str_stats_atk_hero=(TextView)findViewById(R.id.str_stats_atk_hero);
        str_stats_atk_hero.setText(str_stats_atk_hero.getText().toString() + " " + mainJoueur.getAttaque());

        // On affiche la defense du joueur
        TextView str_stats_def_hero=(TextView)findViewById(R.id.str_stats_def_hero);
        str_stats_def_hero.setText(str_stats_def_hero.getText().toString()+" "+mainJoueur.getDefense());

        // On affiche la chance du joueur
        TextView str_stats_luck_hero=(TextView)findViewById(R.id.str_stats_luck_hero);
        str_stats_luck_hero.setText(str_stats_luck_hero.getText().toString()+" "+mainJoueur.getChance());

        // On instancie la fabriqueMonstre et un monstre aléatoirement
        fabriqueMonstre=FabriqueMonstre.getUniqueInstance();
        final Monstre monster = random_monster();

        // On affiche le niveau du monstre
        TextView str_stats_lvl_monster=(TextView)findViewById(R.id.str_stats_lvl_monster);
        str_stats_lvl_monster.setText(str_stats_lvl_monster.getText().toString() + " " + monster.getNiveau());

        //On met la vie du monstre dans la variable intermédiaire
        Life_monster = monster.getVie();

        // On affiche la vie du monstre (qui sera modifiée)
        final TextView life_to_update_monster=(TextView)findViewById(R.id.life_to_update_monster);
        life_to_update_monster.setText(monster.getVie() + "");

        // On affiche la vie du monstre (fixe)
        TextView life_fix_monster=(TextView)findViewById(R.id.life_fix_monster);
        life_fix_monster.setText(monster.getVie() + "");

        // On affiche l'attaque du monstre
        TextView str_stats_atk_monster=(TextView)findViewById(R.id.str_stats_atk_monster);
        str_stats_atk_monster.setText(str_stats_atk_monster.getText().toString() + " " + monster.getAttaque());

        // On affiche la defense du monstre
        TextView str_stats_def_monster=(TextView)findViewById(R.id.str_stats_def_monster);
        str_stats_def_monster.setText(str_stats_def_monster.getText().toString()+" "+monster.getDefense());

        // On affiche la chance du monstre
        TextView str_stats_luck_monster=(TextView)findViewById(R.id.str_stats_luck_monster);
        str_stats_luck_monster.setText(str_stats_luck_monster.getText().toString()+" "+monster.getChance());

        // Création de l'action clic pour attaquer
        Button btn_atk=(Button)findViewById(R.id.btn_atk);
        btn_atk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atk(Life_hero, mainJoueur.getAttaque(), mainJoueur.getDefense(), mainJoueur.getChance(), Life_monster, monster.getAttaque(), monster.getDefense(), monster.getChance(), monster.getNiveau());
            }
        });

        // Création de l'action clic pour se défendre
        Button btn_def=(Button)findViewById(R.id.btn_def);
        btn_def.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                def(Life_hero, mainJoueur.getAttaque(), mainJoueur.getDefense(), mainJoueur.getChance(), Life_monster, monster.getAttaque(), monster.getDefense(), monster.getChance(), monster.getNiveau());
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

    // En pause, le jeu sauvegarde le joueur dans la base de donnée
    public void onPause(){
        super.onPause();
        gestionnaireJoueur=GestionnaireJoueur.getUniqueInstance(getApplicationContext());
        gestionnaireJoueur.saveJoueur(mainJoueur.getId());
    }

    // Renvoi un nombre aléatoire entre min et max
    public int Random(int min, int max)
    {
        max++;
        return (int)(Math.random() * (max-min)) + min;
    }

    // Renvoi si l'attaque est manquée ou pas
    public boolean miss(double luck)
    {
        // L'attaque est manquée de base
        boolean is_missed = true;

        // On récupère un nombre entre zéro et 100
        int random = Random(0, 100);
        // Si ce nombre est "dans la chance" du personnage
        if(random < luck)
        {
            // L'attaque est effectuée
            is_missed = false;
        }

        return is_missed;
    }

    // Renvoi true ou false aléatoirement
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    // Renvoi un monstre aléatoire
    public Monstre random_monster()
    {
        // Récupère la liste de monstre disponible
        List<Monstre> listMonstres = fabriqueMonstre.getMonstres(getApplicationContext());
        // Récupère le nombre de monstre à l'intérieur de cette liste
        int sizeList = listMonstres.size();
        // Récupère aléatoirement l'index (id) d'un monstre entre 0 et le nombre de monstre dans la liste (-1)
        int monster_index = Random(0, sizeList-1);

        // Renvoi le monstre d'index monster_index
        return fabriqueMonstre.getMonstre(monster_index, getApplicationContext());
    }

    // Renvoi la vie qu'il reste au personnage grâce à sa défense et l'attaque de l'adversaire
    public int damages(int life, int atk, int def)
    {
        int life_after = 0;
        // Si l'attaque fait moins de dégat que la defense adverse
        if(def > atk)
        {
            // La vie ne change pas
            life_after = life;
        }
        else
        {
            // Sinon on compte les dégats infligés et la vie chance
            int hit = atk - def;
            life_after = life - hit;
        }
        return life_after;
    }

    // Action d'attaque
    public void atk(int life_hero, int atk_hero, int def_hero, int luck_hero, int life_monster, int atk_monster, int def_monster, int luck_monster, int level_monster)
    {
        // Récupère son le hero à manqué son attaque ou pas
        Boolean hero_missed = miss(luck_hero);

        // Récupère si le monstre attaque ou pas
        Boolean attack_ennemy = getRandomBoolean();

        // Si il attaque
        if(attack_ennemy)
        {
            // Récupère si le monstre à manqué son attaque ou pas
            Boolean ennemy_missed = miss(luck_monster);

            // Si il manque son attaque
            if(ennemy_missed)
            {
                // Et que le hero ne manque pas la sienne
                if(!hero_missed)
                {
                    // Applique les dommages et effectue la fin de tour
                    life_monster = damages(life_monster, atk_hero, 0);
                    end_turn(life_hero, life_monster, level_monster);
                }
            }
            else
            {
                // S'il ne manque pas, et que le hero manque son attaque
                if(hero_missed)
                {
                    // Applique les dommages et effectue la fin de tour
                    life_hero = damages(life_hero, atk_monster, 0);
                    end_turn(life_hero, life_monster, level_monster);
                }
                else
                {
                    // S'il ne manque pas, et que le hero ne manque pas son attaque
                    // Applique les dommages et effectue la fin de tour
                    life_hero = damages(life_hero, atk_monster, 0);
                    life_monster = damages(life_monster, atk_hero, 0);
                    end_turn(life_hero, life_monster, level_monster);
                }
            }
        }
        else
        {
            // Sinon, applique les dommages et effectue la fin de tour
            life_monster = damages(life_monster, atk_hero, def_monster);
            end_turn(life_hero, life_monster, level_monster);
        }
    }

    // Action de défense
    public void def(int life_hero, int atk_hero, int def_hero, int luck_hero, int life_monster, int atk_monster, int def_monster, int luck_monster, int level_monster)
    {
        // Récupère si le monstre attaque ou pas
        Boolean attack_ennemy = getRandomBoolean();

        // S'il attaque
        if(attack_ennemy)
        {
            // Récupère si le monstre manque son attaque ou pas
            Boolean ennemy_missed = miss(luck_monster);

            //S'il ne manque pas,
            if(!ennemy_missed)
            {
                // Applique les dommages et effectue la fin de tour
                life_hero = damages(life_hero, atk_monster, def_hero);
                end_turn(life_hero, life_monster, level_monster);
            }
        }
    }

    // Gère la fin de tour
    public void end_turn(int life_hero, int life_monster, int level_monster)
    {
        // Modifie la vie du hero après application des dégats dans le texte
        TextView life_to_update_hero=(TextView)findViewById(R.id.life_to_update_hero);
        life_to_update_hero.setText(""+life_hero);
        // Et dans la variable intermédiaire
        Life_hero = life_hero;

        // Modifie la vie du monstre après application des dégats dans le texte
        TextView life_to_update_monster=(TextView)findViewById(R.id.life_to_update_monster);
        life_to_update_monster.setText(""+life_monster);
        // Et dans la variable intermédiaire
        Life_monster = life_monster;

        // Si la vie du montre est à 0
        if(life_monster <= 0)
        {
            int xp_win = level_monster * 10000;
            Toast.makeText(getApplicationContext(), "Bien joué ! Vous gagnez " + xp_win + "XP", Toast.LENGTH_SHORT).show();
            mainJoueur.addXp(xp_win);
            goToMap();
        }

        // Si la vie du hero est à 0
        if(life_hero <= 0) {
            Toast.makeText(getApplicationContext(), "Vous avez perdu le combat. ", Toast.LENGTH_SHORT).show();
            goToMap();
        }
    }

    public void goToMap()
    {
        Intent mapIntent=new Intent(this,MainActivity.class);
        startActivity(mapIntent);
    }
}
