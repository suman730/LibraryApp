package com.example.libraryuserapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryuserapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteUser extends AppCompatActivity {

    EditText delete_username;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delete_user );

        delete_username = findViewById( R.id.delete_username );
        deleteBtn = findViewById( R.id.deleteBtn );

        deleteBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference drUser = FirebaseDatabase.getInstance().getReference("Users").child( "username" );
                drUser.removeValue();

                Toast.makeText( DeleteUser.this,"User is Deleted",Toast.LENGTH_LONG ).show();
            }
        } );
    }
    public void back(View view) {
        Intent back = new Intent(getApplicationContext(),AdminDashboard.class);
        startActivity( back );
    }
}