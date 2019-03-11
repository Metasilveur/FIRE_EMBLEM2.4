package com.example.fire_emblem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private List<Skills> listSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_description);

        TextView name_d  = (TextView) findViewById(R.id.name_d);
        TextView hp  = (TextView) findViewById(R.id.hp);
        TextView atq  = (TextView) findViewById(R.id.atq);
        TextView spd  = (TextView) findViewById(R.id.spd);
        TextView def  = (TextView) findViewById(R.id.def);
        TextView res  = (TextView) findViewById(R.id.res);

        TextView moveType_d  = (TextView) findViewById(R.id.moveType_d);
        ImageView image_d = findViewById(R.id.image_d);

        Intent intent = getIntent();
        listSkills = (List<Skills>) intent.getSerializableExtra("LIST");

        name_d.setText(intent.getStringExtra("MyName"));
        hp.setText("Hp : "+intent.getStringExtra("hp"));
        atq.setText("Attaque : "+intent.getStringExtra("atq"));
        spd.setText("Vitesse : "+intent.getStringExtra("spd"));
        def.setText("Défense : "+intent.getStringExtra("def"));
        res.setText("Résistance : "+intent.getStringExtra("res"));

        moveType_d.setText(intent.getStringExtra("MyMoveType"));


        name_d.setTextColor(Color.WHITE);
        moveType_d.setTextColor(Color.WHITE);
        hp.setTextColor(Color.WHITE);
        atq.setTextColor(Color.WHITE);
        spd.setTextColor(Color.WHITE);
        def.setTextColor(Color.WHITE);
        res.setTextColor(Color.WHITE);

        Glide.with(this)
                .load(intent.getStringExtra("Portrait"))
                .apply(RequestOptions.circleCropTransform())
                .into(image_d);

        ImageView bleu = (ImageView) findViewById(R.id.bleu);
        bleu.setImageAlpha(85);

        ImageView green_atq = findViewById(R.id.green_atq);
        ImageView green_hp = findViewById(R.id.green_hp);
        ImageView green_res = findViewById(R.id.green_res);
        ImageView green_spd = findViewById(R.id.green_spd);
        ImageView green_def = findViewById(R.id.green_def);

        green_atq.setScaleX(Float.parseFloat(intent.getStringExtra("atq"))/Integer.parseInt(intent.getStringExtra("maxAtq")));
        green_hp.setScaleX(Float.parseFloat(intent.getStringExtra("hp"))/Integer.parseInt(intent.getStringExtra("maxHp")));
        green_def.setScaleX(Float.parseFloat(intent.getStringExtra("def"))/Integer.parseInt(intent.getStringExtra("maxDef")));
        green_res.setScaleX(Float.parseFloat(intent.getStringExtra("res"))/Integer.parseInt(intent.getStringExtra("maxRes")));
        green_spd.setScaleX(Float.parseFloat(intent.getStringExtra("spd"))/Integer.parseInt(intent.getStringExtra("maxSpd")));

        Button LeBoutton = (Button) findViewById(R.id.boutton);

        LeBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MyIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                MyIntent.putExtra("LIST", (Serializable) listSkills);

                startActivity(MyIntent);
            }
        });
    }
}
