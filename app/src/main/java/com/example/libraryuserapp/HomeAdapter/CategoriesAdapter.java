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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> categoriesBook;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categoriesBook) {
        this.categoriesBook = categoriesBook;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.categories_card_design,parent,false);
        CategoriesViewHolder categoriesViewHolder =new  CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {


        CategoriesHelperClass categoriesHelperClass = categoriesBook.get( position );

        holder.image.setImageResource( categoriesHelperClass.getImage());
        holder.title.setText( categoriesHelperClass.getTitle());

    }

    @Override
    public int getItemCount() {
        return categoriesBook.size();
    }


    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;

        public CategoriesViewHolder(@NonNull View itemView) {
            super( itemView );

            //Hooks

            image = itemView.findViewById( R. id.c_image);
            title = itemView.findViewById( R.id.c_title);
        }
    }
}
