package com.example.idillika;



import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;


public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    CheckBox mFavorite;
    TextView mTitle,mBrand,mPrice;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image_2);
        this.mFavorite = itemView.findViewById(R.id.favorite);
        this.mTitle = itemView.findViewById(R.id.titlet);
        this.mBrand = itemView.findViewById(R.id.brand);
        this.mPrice = itemView.findViewById(R.id.price);


    }

    public void bind(Clothes model,MyAdapter adapter){
        mTitle.setText(model.getTitle());
        mBrand.setText(model.getBrand());
        Picasso.get().load(model.getImageLink()).fit().into(mImageView);
        mPrice.setText(mPrice.getContext().getString(R.string.ruble, model.getPrice()));
        String id = model.getId();
        mFavorite.setChecked(adapter.getValue(model.getId()));
        mFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            adapter.updateValue(id, isChecked);
        });

    }
}
