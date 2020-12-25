package com.example.idillika;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    ArrayList<Clothes> models;
    SharedPreferences mPrefs;

    public MyAdapter(SharedPreferences mPrefs , ArrayList<Clothes> models){
        this.models = models;
        this.mPrefs = mPrefs;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bind(models.get(position), MyAdapter.this);
    }
     public void updateValue(MyHolder holder ,int newValue,String id) {
        mPrefs.edit().putInt(id, newValue).apply();
        if(holder.mFavorite.isClickable()) {
                if (holder.mBoolean) {
                    holder.mFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    holder.mBoolean = true;
                } else {
                    holder.mFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    holder.mBoolean = false;
                }
        }
    }
    public int getValue(MyAdapter adapter,String id){
        return adapter.mPrefs.getInt(id,0);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


}
