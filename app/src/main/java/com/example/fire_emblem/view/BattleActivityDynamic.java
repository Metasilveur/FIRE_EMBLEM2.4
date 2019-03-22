package com.example.fire_emblem.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fire_emblem.R;
import com.example.fire_emblem.model.Character;

import java.io.Serializable;

import static android.graphics.Color.WHITE;

public class BattleActivityDynamic extends AppCompatActivity {

    Character Fighter1;
    Character Fighter2;

    TextView fighter1_hp;
    TextView fighter2_hp;

    int fighter1_hp_int;
    int fighter2_hp_int;

    int fighter2_def_int;
    int fighter1_def_int;

    int fighter1_atq_int;
    int fighter2_atq_int;

    int turn;
    int actif1=0;
    int actif2=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.battle_dynamic);

        Fighter1 = (Character) getIntent().getSerializableExtra("Fight1");
        Fighter2 = (Character) getIntent().getSerializableExtra("Fight2");


        fighter1_hp = findViewById(R.id.fighter1_hp);
        fighter2_hp = findViewById(R.id.fighter2_hp);

        fighter1_hp_int = Integer.parseInt(Fighter1.getGrowths().getHp());
        fighter2_hp_int = Integer.parseInt(Fighter2.getGrowths().getHp());

        fighter2_def_int = Integer.parseInt(Fighter2.getGrowths().getDef());
        fighter1_def_int = Integer.parseInt(Fighter1.getGrowths().getDef());

        fighter1_atq_int = Integer.parseInt(Fighter1.getGrowths().getAtk());
        fighter2_atq_int = Integer.parseInt(Fighter2.getGrowths().getAtk());


        fighter1_hp.setText(Fighter1.getGrowths().getHp()+" / "+Fighter1.getGrowths().getHp());
        fighter2_hp.setText(Fighter2.getGrowths().getHp()+" / "+Fighter2.getGrowths().getHp());

        fighter1_hp.setTextColor(WHITE);
        fighter2_hp.setTextColor(WHITE);

        ImageView fighter1 = findViewById(R.id.fighter1_d);
        ImageView fighter2 = findViewById(R.id.fighter2_d);

        Glide.with(this)
                .load(Fighter1.getAssets().getPortrait().getPx113())
                .apply(RequestOptions.circleCropTransform())
                .into(fighter2);

        Glide.with(this)
                .load(Fighter2.getAssets().getPortrait().getPx113())
                .apply(RequestOptions.circleCropTransform())
                .into(fighter1);

        if(Integer.parseInt(Fighter1.getGrowths().getSpd())>Integer.parseInt(Fighter1.getGrowths().getSpd())) {
            turn=1;
        }
        else {
            turn=2;
        }

        setAnimationR();

        /*while(turn!=0){
            if(turn==1)
            {
                if(actif1==0)
                {
                    setAnimationR();
                }
            }
            else if(turn==2)
            {
                if(actif2==0)
                {
                    setAnimationL();
                }
            }
        }*/



        /*fighter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationR();
            }
        });
        fighter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationL();
            }
        });*/

        Button LeBoutton = (Button) findViewById(R.id.historic);

        LeBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MyIntent = new Intent(BattleActivityDynamic.this, BattleActivity.class);

                MyIntent.putExtra("Fight1", (Serializable) Fighter1);
                MyIntent.putExtra("Fight2", (Serializable) Fighter2);
                startActivity(MyIntent);
            }
        });

    }

    public void setAnimationR(){

        ImageView animation = findViewById(R.id.anim_test);

        animation.setBackgroundResource(R.drawable.anim_slash);

        final AnimationDrawable slashAnim = (AnimationDrawable) animation.getBackground();

        slashAnim.setOneShot(true);

        AlphaAnimation TEST = new AlphaAnimation(1.0f,0.0f);
        TEST.setDuration(1000);
        TEST.setFillAfter(true);



        TEST.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //actif1=1;
                slashAnim.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slashAnim.stop();

                int damage = fighter1_atq_int - fighter2_def_int;
                if(damage < 0)
                    damage = 1;

                fighter2_hp_int -= damage;
                if(fighter2_hp_int>0) {
                    fighter2_hp.setText(fighter2_hp_int + " / " + Fighter2.getGrowths().getHp());
                    setAnimationL();
                }
                else{
                    fighter2_hp.setText("0 / " + Fighter2.getGrowths().getHp());
                }
                /*actif1=0;
                turn = 2;
                if(fighter2_hp_int<0)
                    turn=0;*/
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.startAnimation(TEST);
    }

    public void setAnimationL(){

        ImageView animation1 = findViewById(R.id.anim_test2);

        animation1.setBackgroundResource(R.drawable.anim_slash);

        final AnimationDrawable slashAnim1 = (AnimationDrawable) animation1.getBackground();

        slashAnim1.setOneShot(true);

        AlphaAnimation TEST1 = new AlphaAnimation(1.0f,0.0f);

        TEST1.setDuration(1000);
        TEST1.setFillAfter(true);

        TEST1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //actif2=1;
                slashAnim1.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slashAnim1.stop();

                int damage = fighter2_atq_int - fighter1_def_int;
                if(damage < 0)
                    damage = 1;

                fighter1_hp_int -= damage;
                if(fighter1_hp_int>0) {
                    fighter1_hp.setText(fighter1_hp_int + " / " + Fighter1.getGrowths().getHp());
                    setAnimationR();
                }
                else{
                    fighter1_hp.setText("0 / " + Fighter1.getGrowths().getHp());
                }
                /*actif2=0;
                turn = 1;
                if(fighter1_hp_int<0)
                    turn = 0;*/
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation1.startAnimation(TEST1);
    }
}
