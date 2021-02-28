package com.example.libraryuserapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.libraryuserapp.HomeAdapter.CategoriesAdapter;
import com.example.libraryuserapp.HomeAdapter.CategoriesHelperClass;
import com.example.libraryuserapp.HomeAdapter.FeaturedAdapter;
import com.example.libraryuserapp.HomeAdapter.FeaturedHelperClass;
import com.example.libraryuserapp.HomeAdapter.MostViewedAdapter;
import com.example.libraryuserapp.HomeAdapter.MostViewedHelperClass;
import com.example.libraryuserapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler;
    RecyclerView mostViewedRecycler, categoriesRecycler;

    RecyclerView.Adapter adapter, adapter1, adapter2;
    ImageView menuIcon;
    LinearLayout contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_dashboard );

        featuredRecycler = findViewById( R.id.featured_recycler );
        mostViewedRecycler = findViewById( R.id.mv_recycler );
        categoriesRecycler = findViewById( R.id.c_recycler );
        menuIcon = findViewById( R.id.menu_icon );
        contentView = findViewById( R.id.content_view );

        drawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.navigation_view );

        navigationDrawer();


        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.setCheckedItem( R.id.nav_home );

        menuIcon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible( GravityCompat.START ))
                    drawerLayout.closeDrawer( GravityCompat.START );
                else drawerLayout.openDrawer( GravityCompat.START );
            }
        } );

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
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
                startActivity( new Intent(getApplicationContext(),UserDashboard.class) );
                break;
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(),AllCategories.class));
                break;
            case R.id.nav_about:
                startActivity( new Intent(getApplicationContext(),About.class) );
                break;
            default:
                break;
        }
        return true;
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize( true );
        featuredRecycler.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );

        ArrayList<FeaturedHelperClass> featuredBooks = new ArrayList<>();

        featuredBooks.add( new FeaturedHelperClass( R.drawable.books1, "Programing language", "Book of Progrming languages" ) );
        featuredBooks.add( new FeaturedHelperClass( R.drawable.mag1, "Magazine", "apj" ) );
        featuredBooks.add( new FeaturedHelperClass( R.drawable.qpaper1, "Question Papers", "All Semester Question Papers" ) );
        featuredBooks.add( new FeaturedHelperClass( R.drawable.report1, "Report", "Project Report" ) );

        adapter = new FeaturedAdapter( featuredBooks );
        featuredRecycler.setAdapter( adapter );

        GradientDrawable gradient1 = new GradientDrawable( GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600} );
    }
    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize( true );
        mostViewedRecycler.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );
        ArrayList<MostViewedHelperClass> mostViewedBooks = new ArrayList<>();


        mostViewedBooks.add( new MostViewedHelperClass( R.drawable.mag1, "Magazine", "APJ" ) );
        mostViewedBooks.add( new MostViewedHelperClass( R.drawable.report1, "Project Report", "Library Management System Using MAD" ) );
        mostViewedBooks.add( new MostViewedHelperClass( R.drawable.books1, "Programing language", "C++ programing language" ) );
        mostViewedBooks.add( new MostViewedHelperClass( R.drawable.report1, "Report", "Project Report" ) );

        adapter1 = new MostViewedAdapter( mostViewedBooks );
        mostViewedRecycler.setAdapter( adapter1 );

        GradientDrawable gradient2 = new GradientDrawable( GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600} );
    }

    private void categoriesRecycler() {
        categoriesRecycler.setHasFixedSize( true );
        categoriesRecycler.setLayoutManager( new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false ) );
        ArrayList<CategoriesHelperClass> categoriesBooks = new ArrayList<>();

        categoriesBooks.add( new CategoriesHelperClass( R.drawable.books1, "All Kind of Books" ) );
        categoriesBooks.add( new CategoriesHelperClass( R.drawable.mag1, "Magazines" ) );
        categoriesBooks.add( new CategoriesHelperClass( R.drawable.qpaper1, "Question Papers" ) );
        categoriesBooks.add( new CategoriesHelperClass( R.drawable.report1, "Project Report" ) );

        adapter2 = new CategoriesAdapter( categoriesBooks );
        categoriesRecycler.setAdapter( adapter2 );

        GradientDrawable gradient2 = new GradientDrawable( GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600} );
    }
}