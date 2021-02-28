package com.example.libraryuserapp.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryuserapp.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedHolder> {

    ArrayList<MostViewedHelperClass> mostViewedBooks;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedBooks) {
        this.mostViewedBooks = mostViewedBooks;
    }

    @NonNull
    @Override
    public MostViewedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.most_viewed_card_design,parent,false );
        MostViewedHolder mostViewedHolder =new  MostViewedHolder(view);
        return mostViewedHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedHolder holder, int position) {
        MostViewedHelperClass mostViewedHelperClass = mostViewedBooks.get( position );

        holder.image.setImageResource(mostViewedHelperClass.getImage());
        holder.title.setText(mostViewedHelperClass.getTitle());
        holder.desc.setText(mostViewedHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return mostViewedBooks.size();
    }

    public static class MostViewedHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc;

        public MostViewedHolder(@NonNull View itemView) {
            super( itemView );

            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById( R.id.mv_desc);
        }
    }
}
