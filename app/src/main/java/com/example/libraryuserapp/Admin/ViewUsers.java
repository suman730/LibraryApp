package com.example.libraryuserapp.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.libraryuserapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewUsers extends AppCompatActivity {

    private RecyclerView usersList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference userListRef;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_users );

        username =getIntent().getStringExtra( "username" );
        usersList =findViewById( R.id.users_list );
        usersList.setHasFixedSize( true );
        layoutManager =new LinearLayoutManager( this );
        usersList.setLayoutManager( layoutManager );

        userListRef = FirebaseDatabase.getInstance().getReference().child( "Users List" ).child( "Admin View" ).child( username ).child( "Users" );
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}