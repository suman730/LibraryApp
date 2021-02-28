package com.example.libraryuserapp.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.libraryuserapp.HomeAdapter.EbookAdapter;
import com.example.libraryuserapp.HomeAdapter.EbookData;
import com.example.libraryuserapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EBook extends AppCompatActivity {

    private RecyclerView ebookrecycler;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_e_book );

        ebookrecycler = findViewById(R.id.ebookrecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    EbookData data = snapshot.getValue( EbookData.class);
                    list.add(data);
                }
                adapter = new EbookAdapter(EBook.this,list);
                ebookrecycler.setLayoutManager(new LinearLayoutManager(EBook.this));
                ebookrecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EBook.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}