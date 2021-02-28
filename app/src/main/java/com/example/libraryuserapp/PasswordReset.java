package com.example.libraryuserapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
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

import java.util.UUID;

public class PasswordReset extends AppCompatActivity {

    Button reset,vcode;
    TextView username,newpassword,reenterpswd,vemail,verification_code;
    private DatabaseReference mDatabaseRef;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String verifycode;

    private static void onClick(View view) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_password_reset );

        reset=(Button)findViewById(R.id.change);
        vcode=(Button)findViewById(R.id.vcode);
        username=(TextView)findViewById(R.id.runame);
        vemail=(TextView)findViewById(R.id.vemail);
        newpassword=(TextView)findViewById(R.id.new_password);
        reenterpswd=(TextView)findViewById(R.id.newreenter);
        verification_code=(TextView)findViewById(R.id.verification_code);

        radioGroup = findViewById(R.id.radioGroup);

        vcode.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.length()!=0 && vemail.length()!=0 )
                {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    Toast.makeText(getApplicationContext(), "Selected Radio Button: " + radioButton.getText(),
                            Toast.LENGTH_SHORT).show();
                    String object_name=radioButton.getText().toString();
                    mDatabaseRef= FirebaseDatabase.getInstance().getReference(""+object_name);

                    final String uname;
                    final String emailVerification;
                    emailVerification=vemail.getText().toString().trim();
                    uname=username.getText().toString().trim();

                    Query checkUser = mDatabaseRef.orderByChild("username").equalTo(uname);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                String emailFromDB = snapshot.child(uname).child("email").getValue(String.class);
                                if (emailFromDB.equals(emailVerification)) {

                                    String usernameFromDB = snapshot.child(uname).child("username").getValue(String.class);
                                    String emailFromDBu = snapshot.child(uname).child("email").getValue(String.class);
                                    String mobileFromDB = snapshot.child(uname).child("mobileNo").getValue(String.class);
                                    String passwordFromDB = snapshot.child(uname).child("password").getValue(String.class);
                                    String reenterpwdFromDB = snapshot.child(uname).child("reenter_password").getValue(String.class);
                                    String imageUrlFromDB = snapshot.child(uname).child("imageUrl").getValue(String.class);

                                    //Generating random string
                                    int leftLimit = 48; // numeral '0'
                                    int rightLimit = 122; // letter 'z'
                                    int targetStringLength = 10;
                                 /*   Random generator = new Random();
                                    String x = (String) (generator.nextInt(96) + 32);
                                    Toast.makeText(getApplicationContext(),verifycode,Toast.LENGTH_LONG).show();*/

                                    verifycode = UUID.randomUUID().toString();
                                    Upload upload = new Upload(verifycode,usernameFromDB, passwordFromDB,reenterpwdFromDB,emailFromDBu,mobileFromDB,imageUrlFromDB);
                                    String uploadId = username.getText().toString();
                                    mDatabaseRef.child(uploadId).setValue(upload);

                                }
                                else{
                                    username.setError("Username for email is wrong");
                                    vemail.setError("Username for email is wrong");
                                    vemail.requestFocus();
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

        reset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                Toast.makeText(getApplicationContext(), "Selected Radio Button: " + radioButton.getText(),
                        Toast.LENGTH_SHORT).show();
                String object_name=radioButton.getText().toString();
                mDatabaseRef= FirebaseDatabase.getInstance().getReference(""+object_name);


                if(username.length()!=0 && vemail.length()!=0  && newpassword.length()!=0 && reenterpswd.length()!=0 && vcode.length()!=0)
                {
                    final String uname,vcodeverify;
                    final String pwd,rpwd;
                    pwd=newpassword.getText().toString().trim();
                    rpwd=reenterpswd.getText().toString().trim();
                    uname=username.getText().toString().trim();
                    vcodeverify=verification_code.getText().toString().trim();

                    Query checkUser = mDatabaseRef.orderByChild("username").equalTo(uname);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                String vcodeFromDB = snapshot.child(uname).child("vcode").getValue(String.class);
                                if (vcodeFromDB.equals(vcodeverify)) {

                                    String usernameFromDB = snapshot.child(uname).child("username").getValue(String.class);
                                    String emailFromDB = snapshot.child(uname).child("email").getValue(String.class);
                                    String mobileFromDB = snapshot.child(uname).child("mobileNo").getValue(String.class);
                                    String imageUrlFromDB = snapshot.child(uname).child("imageUrl").getValue(String.class);
                                    //String vcodeFromDB = snapshot.child(uname).child("vcode").getValue(String.class);

                                    Upload upload = new Upload(vcodeFromDB,usernameFromDB,
                                            pwd,rpwd,emailFromDB,mobileFromDB,imageUrlFromDB);
                                    String uploadId = username.getText().toString();
                                    mDatabaseRef.child(uploadId).setValue(upload);
                                    if(mDatabaseRef!=null){Toast.makeText(getApplicationContext(),"Password Reset Successfull",Toast.LENGTH_LONG).show();}
                                    else{
                                        Toast.makeText(getApplicationContext(),"Error!! Something Went wrong",Toast.LENGTH_LONG).show();
                                    }

                                }
                                else{
                                    verification_code.setError("verification code is wrong");
                                    verification_code.requestFocus();
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
    }
}