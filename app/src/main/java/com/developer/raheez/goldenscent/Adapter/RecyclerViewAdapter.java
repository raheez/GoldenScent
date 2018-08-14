package com.developer.raheez.goldenscent.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

    public int getScreenWidth() {

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
         Point size = new Point();
        display.getSize(size);

         return size.x;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

         View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false );
        view.getLayoutParams().width = (int) (getScreenWidth() / 3); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        int positionInList = i % mImageUrls.size();

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImageUrls.get(i))
//                .into(viewHolder.imageView);
//


        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(positionInList))
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
//        return mImageUrls.size();
        return Integer.MAX_VALUE;

    }



    public class ViewHolder extends RecyclerView.ViewHolder{

         ImageView imageView;

         public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_icon);


        }
    }
}
