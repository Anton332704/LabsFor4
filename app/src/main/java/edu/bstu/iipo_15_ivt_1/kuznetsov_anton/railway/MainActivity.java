package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.railway;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView leftListView;
    Context base;
    ArrayList<ItemMenu> itemsDraws;
    SharedPreferences sp;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        drawerToggle = setupDrawerToggle();
        base = this;
        itemsDraws = new ArrayList<ItemMenu>();
        leftListView = (ListView)findViewById(R.id.ListDLeft);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setBackgroundResource(R.drawable.background_color);
        itemsDraws.add(new ItemMenu(""));
        itemsDraws.add(new ItemMenu(getString(R.string.inter_urban_trains)));
        itemsDraws.add(new ItemMenu(getString(R.string.sub_urban_trains)));
        itemsDraws.add(new ItemMenu(getString(R.string.special)));

        DrawerAdapter drawerAdapter = new DrawerAdapter(base, itemsDraws);
        leftListView.setAdapter(drawerAdapter);
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment myFragment = null;
                switch (position) {
                    case 1:
                        setTitle(getString(R.string.inter_urban_trains));
                        Singleton singleton = Singleton.INSTANCE;
                        singleton.far = "1";
                        myFragment = new InterurbanFrag();
                        break;
                    case 2:
                        setTitle(getString(R.string.sub_urban_trains));
                        Singleton singleton1 = Singleton.INSTANCE;
                        singleton1.far = "0";
                        myFragment = new SuburbanFrag();
                        break;
                    case 3:
                        setTitle(getString(R.string.special));
                        myFragment = new MyTripsFrag();
                        break;
                }
                if(myFragment != null) {
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFrame, myFragment).commit();
                }
                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    protected void onResume() {
        sp = PreferenceManager.getDefaultSharedPreferences(base);
        boolean theme = sp.getBoolean("themeDark", false);
        if(theme == true)
        {
            drawerLayout.setBackgroundResource(R.drawable.background_color_dark);
        }
        else drawerLayout.setBackgroundResource(R.drawable.background_color);
        super.onResume();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.add_ok, R.string.next);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(base, Preferences.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
