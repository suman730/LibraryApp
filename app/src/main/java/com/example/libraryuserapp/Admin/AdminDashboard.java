package com.example.libraryuserapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.libraryuserapp.MainActivity;
import com.example.libraryuserapp.R;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    ImageView menuIcon;
    LinearLayout contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_admin_dashboard );

        menuIcon = findViewById( R.id.menu_icon );
        contentView = findViewById( R.id.content_view );

        drawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.navigation_view );

        navigationDrawer();
    }

    private void navigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorAccent));
        drawerLayout.addDrawerListener( new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX( offsetScale );
                contentView.setScaleY( offsetScale );

                //Translate the view accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        } );
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity( new Intent(getApplicationContext(), AdminDashboard.class) );
                break;
            case R.id.nav_logout:
                startActivity( new Intent(getApplicationContext(), MainActivity.class) );
                break;

        }
        return true;
    }

    public void addBooks(View view) {
        Intent book = new Intent(getApplicationContext(), UploadData.class);
        startActivity( book );
    }

    public void addMagazine(View view) {
        Intent magazine = new Intent(getApplicationContext(),UploadData.class);
        startActivity( magazine );
    }

    public void addPaper(View view) {
        Intent qpaper = new Intent(getApplicationContext(), UploadPapers.class);
        startActivity( qpaper );
    }

    public void addReport(View view) {
        Intent report = new Intent(getApplicationContext(),UploadData.class);
        startActivity( report );
    }

    public void viewUser(View view) {
        Intent viewUser = new Intent(getApplicationContext(), ViewUsers.class);
        startActivity( viewUser );
    }

    public void deleteUser(View view) {
        Intent deleteUser = new Intent(getApplicationContext(), DeleteUser.class);
        startActivity( deleteUser );
    }
}