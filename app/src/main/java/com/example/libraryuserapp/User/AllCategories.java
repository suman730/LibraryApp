package com.example.libraryuserapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.libraryuserapp.R;

public class AllCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_all_categories );
    }

    public void back(View view) {
    }

    public void selectBook(View view) {
        startActivity( new Intent(getApplicationContext(),ViewData.class) );
    }

    public void selectMag(View view) {
        startActivity( new Intent(getApplicationContext(),ViewData.class) );
    }

    public void selectReport(View view) {
        startActivity( new Intent(getApplicationContext(),ViewData.class) );
    }

    public void selectPaper(View view) {
        startActivity( new Intent(getApplicationContext(),GalleryFragment.class) );
    }
}