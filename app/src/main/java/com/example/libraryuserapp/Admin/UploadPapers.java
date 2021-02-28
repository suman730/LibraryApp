package com.example.libraryuserapp.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.libraryuserapp.HelperClass;
import com.example.libraryuserapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadPapers extends AppCompatActivity {

    private CardView addPaper;
    private ImageView paperImageView;
    private EditText paper_name;
    private Button uploadPaperBtn;

    private final int REQ = 1;
    private Bitmap bitmap;
    private DatabaseReference reference;
    private StorageReference storageReference;

    String downloadUrl = "";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_upload_papers );

        reference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        addPaper.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        } );

        uploadPaperBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paper_name.getText().toString().isEmpty()){
                    paper_name.setError( "Empty" );
                    paper_name.requestFocus();
                }else if (bitmap == null){
                    uploadData();
                }else {
                    uploadImage();
                }
            }
        } );
    }

    private void uploadImage() {
        pd.setMessage( "Uploading...." );
        pd.show();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream );
        byte[] finalImage = byteArrayOutputStream.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child( "Book" ).child( finalImage+"jpg" );
        final UploadTask uploadTask = filePath.putBytes( finalImage );
        uploadTask.addOnCompleteListener( UploadPapers.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf( uri );
                                    uploadData();
                                }
                            } );
                        }
                    } );
                }else {
                    pd.dismiss();
                    Toast.makeText( UploadPapers.this,"Something went Wrong",Toast.LENGTH_SHORT ).show();
                }

            }
        } );
    }

    private void uploadData(){

        reference = reference.child( "Question Paper" );
        final String uniquekey = reference.push().getKey();

        String name = paper_name.getText().toString();

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format( calForDate.getTime() );

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format( calForTime.getTime() );

        HelperClass bookHelperClass = new HelperClass(name,downloadUrl,date,time,uniquekey);
        reference.child( uniquekey ).setValue( bookHelperClass ).addOnSuccessListener( new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText( UploadPapers.this,"Book Uploaded",Toast.LENGTH_SHORT ).show();
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText( UploadPapers.this,"Something went wrong",Toast.LENGTH_SHORT ).show();
            }
        } );

    }
    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult( pickImage,REQ );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == REQ && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(),uri );
            } catch (IOException e) {
                e.printStackTrace();
            }
            paperImageView.setImageBitmap( bitmap );
        }
    }

    public void back(View view) {
        startActivity( new Intent(getApplicationContext(),AdminDashboard.class) );
        finish();
    }
}