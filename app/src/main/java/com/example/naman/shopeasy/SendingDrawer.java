package com.example.naman.shopeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class SendingDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    ImageView img1,img2,img3,img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //imgview java obje
        img1= (ImageView) findViewById(R.id.iv1);
        img2= (ImageView) findViewById(R.id.iv2);
        img3= (ImageView) findViewById(R.id.iv3);
        img4= (ImageView) findViewById(R.id.iv4);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sending_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.e1) {
            Intent intent=new Intent(this,ElectronicsActivity1.class);
            startActivity(intent);
        } else if (id == R.id.f1) {
            Intent intent=new Intent(this,FashionActivity1.class);
            startActivity(intent);
        } else if (id == R.id.hk1) {
            Intent intent=new Intent(this,HomeAndKitchenActivity1.class);
            startActivity(intent);
        } else if (id == R.id.hb1) {
            Intent intent=new Intent(this,HealthAndBeautyActivity1.class);
            startActivity(intent);
        } else if (id == R.id.h1) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv1)
        {
            Intent intent=new Intent(this,ElectronicsActivity1.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.iv2)
        {
            Intent intent=new Intent(this,FashionActivity1.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.iv3)
        {
            Intent intent=new Intent(this,HomeAndKitchenActivity1.class);
            startActivity(intent);
        }
        if (v.getId()==R.id.iv4)
        {
            Intent intent=new Intent(this,HealthAndBeautyActivity1.class);
            startActivity(intent);
        }

    }
}
