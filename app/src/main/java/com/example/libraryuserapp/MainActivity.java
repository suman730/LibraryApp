package com.example.libraryuserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.libraryuserapp.User.UserDashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static String hostname="hostname";
    EditText username,password;
    Button login;
    Button create_account,reset_password;
    private DatabaseReference mDatabaseRef;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        username=(EditText) findViewById(R.id.uname);
        password=(EditText) findViewById(R.id.loginpassword);
        login=(Button)findViewById(R.id.login);
        create_account=(Button) findViewById(R.id.signup);
        reset_password=(Button) findViewById(R.id.forgotpwd);

        radioGroup = findViewById(R.id.radioGroup);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Top Performers");

        create_account.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateAccount.class);
                startActivity( intent );
            }
        } );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId =radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById( radioId );

                Toast.makeText( getApplicationContext(),"Hello",Toast.LENGTH_LONG ).show();

                final String object_name = radioButton.getText().toString();
                mDatabaseRef = FirebaseDatabase.getInstance().getReference(""+object_name);
                Toast.makeText( getApplicationContext(),object_name,Toast.LENGTH_LONG ).show();


                if(username.length()!=0 && password.length()!=0)
                {
                    final String uname;
                    final String pwd;
                    pwd=password.getText().toString().trim();
                    uname=username.getText().toString().trim();
                    Query checkUser = mDatabaseRef.orderByChild("username").equalTo(uname);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                String passwordFromDB = snapshot.child(uname).child("password").getValue(String.class);
                                if (passwordFromDB.equals(pwd)) {
                                    username.setError(null);
                                    String usernameFromDB = snapshot.child(uname).child("username").getValue(String.class);
                                    String emailFromDB = snapshot.child(uname).child("email").getValue(String.class);
                                    String mobileFromDB = snapshot.child(uname).child("mobileNo").getValue(String.class);

                                    //     String emailFromDB = snapshot.child(uname).child("email").getValue(String.class);

                                    if(object_name.equals("User"))
                                    {

                                        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                                        // Passing data to multiple activities
                                        startActivity(intent);



                                    }
                                    else{

                                        Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                                        startActivity(intent);




                                    }


                                }
                                else{
                                    username.setError("Username for password is wrong");
                                    password.setError("Username for password is wrong");
                                    password.requestFocus();
                                }
                            } else {
                                username.setError("Username Not Found");
                                username.requestFocus();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Connection to database failed", Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else{

                    Toast.makeText(getApplicationContext(),"Some fields are empty!!",Toast.LENGTH_LONG).show();
                }
            }
        } );

        reset_password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PasswordReset.class);
                startActivity(intent);
            }
        } );
    }
}