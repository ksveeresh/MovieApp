package com.example.moviesapp.module;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.FavoriteActivity;
import com.example.moviesapp.MovieDisplayActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.pojo.Search;

import java.util.ArrayList;

public class FavoritRecyclerViewAdapter extends RecyclerView.Adapter<FavoritRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Search> data;

    public FavoritRecyclerViewAdapter(Context context, ArrayList<Search> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(!data.get(position).getTitle().equals("No Movies Found")){
            holder.txttitle.setText(data.get(position).getTitle());
            holder.txtdate.setText(data.get(position).getYear());

        }else{
            holder.txttitle.setText(data.get(position).getTitle());

        }

        Glide.with(context).load(data.get(position).getPoster()).placeholder(R.drawable.ic_broken_image).into(holder.imageView);
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!data.get(position).getTitle().equals("No Movies Found")){
                    Intent i = new Intent(context, MovieDisplayActivity.class);
                    i.putExtra("Movie_id",data.get(position).getImdbID());
                    i.putExtra("Movie_name",data.get(position).getTitle());
                    context.startActivity(i);
                }
           }
        });



    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txttitle;
        private  TextView txtdate;
        private LinearLayout mContainer;
        private LinearLayout mContainer1;
        private ImageView imageView;
        private ImageView mfavorit;

        ViewHolder(final View itemView) {
            super(itemView);

            txttitle = itemView.findViewById(R.id.textView);
            txtdate = itemView.findViewById(R.id.timeing);
            mContainer = itemView.findViewById(R.id.cardview);
            mContainer1 = itemView.findViewById(R.id.cardview1);
            imageView = itemView.findViewById(R.id.imageView);
            mfavorit = itemView.findViewById(R.id.favorit);

        }
    }

}
