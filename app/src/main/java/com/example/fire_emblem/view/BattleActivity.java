package com.example.fire_emblem.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fire_emblem.R;
import com.example.fire_emblem.controller.BattleAdapter;
import com.example.fire_emblem.model.Battle;
import com.example.fire_emblem.model.Character;

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

        int hp_Fighter1 = Integer.parseInt(Fighter1.getGrowths().getHp());
        int atq_Fighter1 = Integer.parseInt(Fighter1.getGrowths().getAtk());
        int def_Fighter1 = Integer.parseInt(Fighter1.getGrowths().getDef());
        int res_Fighter1 = Integer.parseInt(Fighter1.getGrowths().getRes());
        int spd_Fighter1 = Integer.parseInt(Fighter1.getGrowths().getSpd());

        int hp_Fighter2 = Integer.parseInt(Fighter2.getGrowths().getHp());
        int atq_Fighter2 = Integer.parseInt(Fighter1.getGrowths().getAtk());
        int def_Fighter2 = Integer.parseInt(Fighter1.getGrowths().getDef());
        int res_Fighter2 = Integer.parseInt(Fighter1.getGrowths().getRes());
        int spd_Fighter2 = Integer.parseInt(Fighter1.getGrowths().getSpd());
/*
        if((Fighter1.getWeaponType().equals("Blue tome"))
        ||(Fighter1.getWeaponType().equals("Red tome"))
        ||(Fighter1.getWeaponType().equals("Green tome"))
        ||(Fighter1.getWeaponType().equals("Colorless tome")))
        {
            def_Fighter2 = res_Fighter2;
        }

        if((Fighter2.getWeaponType().equals("Blue tome"))
                ||(Fighter2.getWeaponType().equals("Red tome"))
                ||(Fighter2.getWeaponType().equals("Green tome"))
                ||(Fighter2.getWeaponType().equals("Colorless tome")))
        {
            def_Fighter1 = res_Fighter1;
        }*/

        int cpt_turn=1;

        while ((hp_Fighter1 > 0) && (hp_Fighter2 > 0))
        {
            String score1 = "";
            String score2 = "";
            String score1_txt = "";
            String score2_txt = "";
            String turn = "TURN ";
            turn += Integer.toString(cpt_turn);
            cpt_turn +=1;

            if(spd_Fighter1 >= spd_Fighter2)
            {
                int damage = def_Fighter2 - atq_Fighter1;
                if(damage<0)
                {
                    damage=1;
                }
                hp_Fighter2 -= damage;

                if(hp_Fighter2 < 0)
                {
                    hp_Fighter2=0;
                }

                score1_txt += Fighter1.getShortName();
                score1_txt += " attacks first and deals ";
                score1_txt+=Integer.toString(damage);
                score1_txt+=" damage(s) !";


                score1+=Integer.toString(hp_Fighter1);
                score1+=" - ";
                score1+=Integer.toString(hp_Fighter2);

                damage = def_Fighter1 - atq_Fighter2;
                if(damage<0)
                {
                    damage=1;
                }
                hp_Fighter1 -= damage;

                if(hp_Fighter1 < 0)
                {
                    hp_Fighter1=0;
                }

                score2_txt += Fighter2.getShortName();
                score2_txt += " attacks in second and deals ";
                score2_txt+=Integer.toString(damage);
                score2_txt+=" damage(s) !";

                score2+=Integer.toString(hp_Fighter1);
                score2+=" - ";
                score2+=Integer.toString(hp_Fighter2);

            }else{
                int damage = def_Fighter1 - atq_Fighter2;
                if(damage<0)
                {
                    damage=1;
                }
                hp_Fighter1 -= damage;

                if(hp_Fighter1 < 0)
                {
                    hp_Fighter1=0;
                }

                score1_txt += Fighter2.getShortName();
                score1_txt += " attacks first and deals ";
                score1_txt+=Integer.toString(damage);
                score1_txt+=" damage(s) !";

                score1+=Integer.toString(hp_Fighter1);
                score1+=" - ";
                score1+=Integer.toString(hp_Fighter2);

                damage = def_Fighter2 - atq_Fighter1;
                if(damage<0)
                {
                    damage=1;
                }
                hp_Fighter2 -= damage;

                if(hp_Fighter2 < 0)
                {
                    hp_Fighter2=0;
                }

                score2_txt += Fighter1.getShortName();
                score2_txt += " attacks in second and deals ";
                score2_txt+=Integer.toString(damage);
                score2_txt+=" damage(s) !";

                score2+=Integer.toString(hp_Fighter1);
                score2+=" - ";
                score2+=Integer.toString(hp_Fighter2);
            }

            battleList.add(new Battle(Fighter1, Fighter2, score1, score2, score1_txt, score2_txt, turn));
        }
        /*battleList.add( new Battle( Fighter1,Fighter2 , "30-40", "22-40"));
        battleList.add( new Battle( Fighter1,Fighter2 , "30-40", "22-40"));
        battleList.add( new Battle( Fighter1,Fighter2 , "30-40", "22-40"));*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_skills);

        mAdapter = new BattleAdapter(this, battleList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
}

