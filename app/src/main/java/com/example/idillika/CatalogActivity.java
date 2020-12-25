package com.example.idillika;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    SharedPreferences mPrefs ;
    private ArrayList<Clothes> clothes =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRecyclerView = findViewById(R.id.recyclerViews);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mPrefs= PreferenceManager
                .getDefaultSharedPreferences(this);

        NetworkService.getInstance()
                .getJsonApi()
                .getClothes()
                .enqueue(new Callback<List<Clothes>>() {
        @Override
        public void onResponse(Call<List<Clothes>> call, Response<List<Clothes>> response) {
            if (response.isSuccessful() && response.body()!=null){
                clothes = new ArrayList<>(response.body());
                myAdapter=new MyAdapter(mPrefs,clothes);
                mRecyclerView.setAdapter(myAdapter);

            }
            Log.d("TAG2", "onResponse"+ (response.body()));
        }

        @Override
        public void onFailure(Call<List<Clothes>> call, Throwable throwable) {
            Log.d("TAG", "Response Failure =" + throwable.toString());
            Toast.makeText(CatalogActivity.this,"Упс! Что то пошло не так", Toast.LENGTH_SHORT).show();
        }
    });

}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_product:
                Intent intent = new Intent(this, KorzinaActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}