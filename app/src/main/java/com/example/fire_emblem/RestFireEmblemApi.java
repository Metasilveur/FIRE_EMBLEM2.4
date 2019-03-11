package com.example.fire_emblem;
import retrofit2.Call;
import retrofit2.http.GET;
import com.example.fire_emblem.RestFireEmblemResponse;

public interface RestFireEmblemApi {

    @GET("FireApi.json")
    Call<RestFireEmblemResponse> getAlllHeroes();
}
