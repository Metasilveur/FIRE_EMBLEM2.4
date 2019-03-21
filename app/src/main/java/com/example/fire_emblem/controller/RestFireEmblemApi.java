package com.example.fire_emblem.controller;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestFireEmblemApi {

    @GET("FireApi.json")
    Call<RestFireEmblemResponse> getAlllHeroes();
}
