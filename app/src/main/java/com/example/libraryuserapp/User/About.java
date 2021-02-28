package com.example.libraryuserapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.libraryuserapp.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );
    }

    public void back(View view) {
        Intent back = new Intent(getApplicationContext(),UserDashboard.class);
        startActivity( back );
    }
}