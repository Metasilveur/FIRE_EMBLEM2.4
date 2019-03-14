package com.example.fire_emblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fire_emblem.controller.BattleAdapter;
import com.example.fire_emblem.controller.SkillsAdapter;

import java.util.ArrayList;
import java.util.List;

public class BattleActivity extends AppCompatActivity {
    private List<Battle> battleList;
    private RecyclerView recyclerView;
    private BattleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skill_list);
        battleList = new ArrayList<>();

        Character Fighter1 = (Character) getIntent().getSerializableExtra("Fight1");
        Character Fighter2 = (Character) getIntent().getSerializableExtra("Fight2");

        battleList.add( new Battle( Fighter1,Fighter2 , "30-40", "22-40"));
        battleList.add( new Battle( Fighter1,Fighter2 , "30-40", "22-40"));
        battleList.add( new Battle( Fighter1,Fighter2 , "30-40", "22-40"));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_skills);

        mAdapter = new BattleAdapter(this, battleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
}

