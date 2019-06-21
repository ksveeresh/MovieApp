package com.example.moviesapp.module;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.MovieDisplayActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.pojo.Search;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Search> data;
    private RecyclerViewAdapter.ClickListener clickListener;
    Context mContext;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener, Context context) {
        this.clickListener = clickListener;
        mContext=context;
        data = new ArrayList<>();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(!data.get(position).getTitle().equals("No Movies Found")){
            holder.txttitle.setText(data.get(position).getTitle());
            holder.txtdate.setText(data.get(position).getYear());
            if(data.get(position).getFlag()==1) {
                Glide.with(mContext).load(R.drawable.ic_favorite).into(holder.mfavorit);
            }else{
                Glide.with(mContext).load(R.drawable.ic_heart).into(holder.mfavorit);

            }
        }else{
            holder.txttitle.setText(data.get(position).getTitle());
            holder.txtdate.setText("");

        }

        Glide.with(mContext).load(data.get(position).getPoster()).into(holder.imageView);
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!data.get(position).getTitle().equals("No Movies Found")){
                    clickListener.launchIntent(data.get(position));
                }

            }
        });
        holder.mContainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get(position).getFlag()==1) {
                    data.get(position).setFlag(0);
                    clickListener.ClickFavorite(data.get(position));
                    notifyDataSetChanged();
                }else{
                    data.get(position).setFlag(1);
                    clickListener.ClickFavorite(data.get(position));
                    notifyDataSetChanged();

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private  TextView txttitle;
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

    public interface ClickListener {
        void launchIntent(Search search);
        void ClickFavorite(Search search);
    }

    public void setData(ArrayList<Search> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}