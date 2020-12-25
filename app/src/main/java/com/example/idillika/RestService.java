package com.example.idillika;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {
    @GET("catalogList.php?section=21&session_id=f3e82db3d0b2bcce07eae17dd9cb46d3")
    Call<List<Clothes>> getClothes();
}
