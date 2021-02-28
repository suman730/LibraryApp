package com.example.libraryuserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateAccount extends AppCompatActivity {

    private String vcode="vcode";
    TextView username,password,reenterpwd,email,mobileno;
    Button create;
    private DatabaseReference mDatabaseRef;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_create_account );

        username=(TextView)findViewById(R.id.uname);
        password=(TextView)findViewById(R.id.password);
        reenterpwd=(TextView)findViewById(R.id.reenter);
        email=(TextView)findViewById(R.id.email);
        mobileno=(TextView)findViewById(R.id.mobile);
        create=(Button)findViewById(R.id.create);

        radioGroup = findViewById(R.id.radioGroup);

        create.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.length()!=0 && password.length()!=0 && reenterpwd.length()!=0 && email.length()!=0 && mobileno.length()!=0)
                {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    Toast.makeText(getApplicationContext(), "Selected Radio Button: " + radioButton.getText(),
                            Toast.LENGTH_SHORT).show();
                    String object_name=radioButton.getText().toString();
                    mDatabaseRef= FirebaseDatabase.getInstance().getReference(""+object_name);

                    String uname;
                    String pwd,rpwd;
                    pwd=password.getText().toString().trim();
                    rpwd=reenterpwd.getText().toString().trim();
                    uname=username.getText().toString().trim();

                    if(mobileno.length()==10){
                        if(pwd.equals(rpwd))
                        {
                            Query checkUser = mDatabaseRef.orderByChild("username").equalTo(uname);
                            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        Toast.makeText(getApplicationContext(), "Username exists", Toast.LENGTH_LONG).show();
                                    } else {
                                        uploadFile();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(getApplicationContext(), "Connection to database failed", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password and reenter password not matching",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Mobile number length grater than 10 or less", Toast.LENGTH_SHORT).show();
                    }
                }
                else{

                    Toast.makeText(getApplicationContext(),"Some fields are empty!!",Toast.LENGTH_LONG).show();
                }
            }
        } );
    }
    private void uploadFile() {
        Upload upload = new Upload(vcode,username.getText().toString().trim(),
                password.getText().toString().trim(),
                reenterpwd.getText().toString().trim(),
                email.getText().toString().trim(),
                mobileno.getText().toString().trim(),"image url");
        String uploadId = username.getText().toString();
        mDatabaseRef.child(uploadId).setValue(upload);

        if(mDatabaseRef!=null)
        {
            Toast.makeText(getApplicationContext(),"Successfully Registered\nThank you for being our part of a family",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Something went Wrong!! Account Not created",Toast.LENGTH_LONG).show();
        }
    }
}