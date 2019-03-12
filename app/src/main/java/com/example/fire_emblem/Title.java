package com.example.fire_emblem;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

public class Title extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_screen);

        ImageView animation = (ImageView) findViewById(R.id.lucina);

        animation.setBackgroundResource(R.drawable.animation);

        final AnimationDrawable LuciAnimation = (AnimationDrawable) animation.getBackground();

        /*
        animation.setScaleX(0.5f);
        animation.setScaleY(0.4f);
        animation.setY(500);
        TranslateAnimation     translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setDuration(3000);
        translateAnimation.setFillEnabled(true);
        translateAnimation.setFillAfter(true);

        translateAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                // TODO Auto-generated method stub
                LuciAnimation.start();
            }

            @Override
            public void onAnimationEnd(Animation arg0)
            {
                Toast.makeText(getApplicationContext(), "Miaow",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
                // TODO Auto-generated method stub

            }
        });

        animation.startAnimation(translateAnimation);*/

        /*animation.setScaleX(0.5f);
        animation.setScaleY(0.4f);
        animation.setY(-500);*/

        LuciAnimation.setOneShot(true);

        LuciAnimation.start();

        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.mainlayout);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Title.this, MainActivity.class));
            }
        });

    }
}
