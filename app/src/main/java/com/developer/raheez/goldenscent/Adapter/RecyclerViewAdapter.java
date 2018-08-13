package com.developer.raheez.goldenscent.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.developer.raheez.goldenscent.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public ArrayList<String> mImageUrls = new ArrayList<>();
    public Context mContext;

     public RecyclerViewAdapter(Context context,ArrayList<String> mImageUrls){

         this.mImageUrls = mImageUrls;
         this.mContext = context;
     }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

         View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false );
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(i))
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

         ImageView imageView;

         public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_icon);


        }
    }
}
