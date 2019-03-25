package com.example.fire_emblem.controller;
import com.example.fire_emblem.model.Character;
import com.example.fire_emblem.model.Skills;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private MainActivity view;
    public List<Skills> listSkills;
    public List<Character> listCharacter;

    public MainController(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void onCreate() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Metasilveur/MobileFireEmblem/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestFireEmblemApi restApi = retrofit.create(RestFireEmblemApi.class);


        Call<RestFireEmblemResponse> call = restApi.getAlllHeroes();

        call.enqueue(new Callback<RestFireEmblemResponse>() {
            @Override
            public void onResponse(Call<RestFireEmblemResponse> call, Response<RestFireEmblemResponse> response) {
                RestFireEmblemResponse restFireEmblemResponse = response.body();
                listCharacter = restFireEmblemResponse.getHeroes();
                listSkills = restFireEmblemResponse.getSkills();
                view.showList(listCharacter);
            }

            @Override
            public void onFailure(Call<RestFireEmblemResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });

    }
}
