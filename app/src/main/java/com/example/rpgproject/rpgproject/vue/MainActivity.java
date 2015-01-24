package com.example.rpgproject.rpgproject.vue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rpgproject.rpgproject.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        SharedPreferences sharedPreferences=getSharedPreferences("position_x",0);


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
