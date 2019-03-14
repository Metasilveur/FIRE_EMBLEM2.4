package com.example.fire_emblem.controller;
import com.example.fire_emblem.Character;
import com.example.fire_emblem.MainActivity;
import com.example.fire_emblem.RestFireEmblemApi;
import com.example.fire_emblem.RestFireEmblemResponse;
import com.example.fire_emblem.SelectCharacter;
import com.example.fire_emblem.Skills;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectController {

    private SelectCharacter view;
    public List<Skills> listSkills;
    public List<Character> listCharacter;

    public SelectController(SelectCharacter mainActivity) {
        this.view = mainActivity;
    }

    public void onCreate() {

        //Pour ceux qui veulent aller plus loin
        //Il faut créer ces objets avec des singletons.
        // Voir le cours de Génie Logiciel -> Singleton
        //Pour ceux qui veulent encore aller plus loin
        //Voir Injection de dépendances
        //On crée un objet gson

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Metasilveur/MobileFireEmblem/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre instance de notre RestAPI Pokemon.
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
