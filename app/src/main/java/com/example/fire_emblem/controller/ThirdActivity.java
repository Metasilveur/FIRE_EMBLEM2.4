package com.example.fire_emblem.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fire_emblem.R;
import com.example.fire_emblem.model.Skills;
import com.example.fire_emblem.view.SkillsAdapter;

import java.util.List;

public class ThirdActivity  extends AppCompatActivity {
    private List<Skills> listSkills;
    private RecyclerView recyclerView;
    private SkillsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skill_list);

        Intent intent = getIntent();
        listSkills = (List<Skills>) intent.getSerializableExtra("LIST");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_skills);
        //recyclerView.setHasFixedSize(true);
        mAdapter = new SkillsAdapter(this, listSkills);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

}
