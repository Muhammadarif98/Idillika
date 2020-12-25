package com.example.idillika;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView,mFavorite;
    TextView mTitle,mBrand,mPrice;
    Boolean mBoolean = false;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_2);
        this.mFavorite = itemView.findViewById(R.id.favorite);
        this.mTitle = itemView.findViewById(R.id.titlet);
        this.mBrand = itemView.findViewById(R.id.brand);
        this.mPrice = itemView.findViewById(R.id.price);


    }

    public void bind(Clothes models,MyAdapter adapter){
        //mBoolean.compareTo(models.getFavorite());
        mTitle.setText(models.getTitle());
        mBrand.setText(models.getBrand());
        Picasso.get().load(models.getImageLink()).fit().into(mImageView);
        mPrice.setText(models.getPrice());
        String id = models.getId();
        adapter.updateValue(this,adapter.getValue(id),id);
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFavorite.isClickable()){
                    adapter.updateValue(MyHolder.this,adapter.getValue(id),id);

                }

            }
        });

    }
//    public void updateValue(MyAdapter adapter ,boolean newValue,String id) {
//        adapter.mPrefs.edit().putBoolean(id, newValue).apply();
//        if(mBoolean){
//            mFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
//            mBoolean = true;
//        }else {
//            mFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//            mBoolean = false;
//        }
//
//    }
//    public boolean getValue(MyAdapter adapter,String id){
//        return adapter.mPrefs.getBoolean(id,true);
//    }


}
