package com.example.libraryuserapp.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.libraryuserapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadData extends AppCompatActivity {

    private CardView addData;
    private Spinner dataCategory;
    private ImageView dataImageView;
    private EditText data_name;
    private Button uploadDataBtn;

    private String category;
    private final int REQ = 1;
    private Uri pdfData;
    private Bitmap bitmap;
    private DatabaseReference reference;
    private StorageReference storageReference;

    String downloadUrl = "";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_upload_data );

        reference = FirebaseDatabase.getInstance().getReference("Data");
        storageReference = FirebaseStorage.getInstance().getReference("Data");

        pd =new ProgressDialog( this );
        addData = findViewById( R.id.addData );
        dataCategory = findViewById( R.id.data_categories );
        uploadDataBtn = findViewById( R.id.uploadDataBtn );
        data_name = findViewById( R.id.dataName );

        String[] items = new String[]{"Select Category", "Books", "Project Report","Magazines"};
        dataCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

        dataCategory.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = dataCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        addData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        } );
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("pdf/docs/ppt");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            pdfData = data.getData();
            Toast.makeText(this, "" + pdfData, Toast.LENGTH_SHORT).show();
        }
    }


    public void back(View view) {
        startActivity( new Intent(getApplicationContext(),AdminDashboard.class) );
    }
}