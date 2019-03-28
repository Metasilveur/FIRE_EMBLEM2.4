package com.example.fire_emblem.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.example.fire_emblem.R;

public class Title extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);

        ImageView animation = (ImageView) findViewById(R.id.lucina);

        animation.setBackgroundResource(R.drawable.animation);

        final AnimationDrawable LuciAnimation = (AnimationDrawable) animation.getBackground();

        LuciAnimation.setOneShot(true);

        LuciAnimation.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.mainlayout);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Title.this, SelectActivity.class));

            }
        });

    }
}
