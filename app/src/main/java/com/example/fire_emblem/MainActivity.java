package com.example.fire_emblem;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {
    private List<Character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CharacterAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this);
        controller.onCreate();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

/*


        mAdapter = new CharacterAdapter(characterList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Character character = characterList.get(position);
                Toast.makeText(getApplicationContext(), ((Character) character).getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        CreateCharacterList();*/
    }

    /*private void CreateCharacterList() {
        Character character = new Character("Abel", "Cavalry", 150);
        characterList.add(character);
        character = new Character("Lucina", "Swordman", 170);
        characterList.add(character);
        character = new Character("Chrom", "Fdp", 165);
        characterList.add(character);
        mAdapter.notifyDataSetChanged();
    }*/

    public void showList(final List<Character> listCharacter) {

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        // define an adapter
        mAdapter = new CharacterAdapter(this, listCharacter);

        Switch testSwitch = findViewById(R.id.switch_choice);

        testSwitch.setChecked(true);

        testSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    for(Character perso : listCharacter)
                    {
                        perso.select = true;
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    for(Character perso : listCharacter)
                    {
                        perso.select = false;
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Character character = listCharacter.get(position);
                //Toast.makeText(getApplicationContext(), "Nique ta mère !", Toast.LENGTH_SHORT).show();
                ImageView perso;
                TextView name_click;
                perso = findViewById(R.id.perso);
                name_click = findViewById(R.id.name_click);

                name_click.setText(character.getShortName());
                name_click.setTextColor(BLACK);

                Glide.with(MainActivity.this)
                        .load(character.getAssets().getPortrait().getPx113())
                        .apply(RequestOptions.circleCropTransform())
                        .into(perso);
            }

            @Override
            public void onLongClick(View view, int position) {

                Character character = listCharacter.get(position);
                int maxHp = 0;
                int maxAtq = 0;
                int maxDef = 0;
                int maxRes = 0;
                int maxSpd = 0;
                //Toast.makeText(getApplicationContext(), ((Character) character).getName() + " is selected, Enculé !", Toast.LENGTH_SHORT).show();
                Intent MyIntent = new Intent(MainActivity.this, SecondActivity.class);

                for(Character charac : listCharacter)
                {
                    if(Integer.parseInt(charac.getGrowths().getHp()) > maxHp)
                        maxHp = Integer.parseInt(charac.getGrowths().getHp());
                    if(Integer.parseInt(charac.getGrowths().getAtk()) > maxAtq)
                        maxAtq = Integer.parseInt(charac.getGrowths().getAtk());
                    if(Integer.parseInt(charac.getGrowths().getDef()) > maxDef)
                        maxDef = Integer.parseInt(charac.getGrowths().getDef());
                    if(Integer.parseInt(charac.getGrowths().getRes()) > maxRes)
                        maxRes = Integer.parseInt(charac.getGrowths().getRes());
                    if(Integer.parseInt(charac.getGrowths().getSpd()) > maxSpd)
                        maxSpd = Integer.parseInt(charac.getGrowths().getSpd());

                }

                MyIntent.putExtra("maxHp", Integer.toString(maxHp));
                MyIntent.putExtra("maxAtq", Integer.toString(maxAtq));
                MyIntent.putExtra("maxDef", Integer.toString(maxDef));
                MyIntent.putExtra("maxRes", Integer.toString(maxRes));
                MyIntent.putExtra("maxSpd", Integer.toString(maxSpd));

                List<Skills> listSkills2 = controller.listSkills;

                MyIntent.putExtra("MyName", character.getName());
                MyIntent.putExtra("MyMoveType", character.getOrigin());
                MyIntent.putExtra("hp", character.getGrowths().getHp());
                MyIntent.putExtra("atq", character.getGrowths().getAtk());
                MyIntent.putExtra("spd", character.getGrowths().getSpd());
                MyIntent.putExtra("def", character.getGrowths().getDef());
                MyIntent.putExtra("res", character.getGrowths().getRes());
                MyIntent.putExtra("Portrait", character.getAssets().getPortrait().getPx150());

                List<Skills> listSkills = new ArrayList<>();

                for(Skills skills : listSkills2)
                {
                    for(Skills skills1 : character.getSkills())
                    {
                        if(skills.getName().equals(skills1.getName()))
                        {
                            listSkills.add(skills);
                        }
                    }
                }

                MyIntent.putExtra("LIST", (Serializable) listSkills);

                startActivity(MyIntent);
                /*Character character = listCharacter.get(position);
                //Toast.makeText(getApplicationContext(), "Nique ta mère !", Toast.LENGTH_SHORT).show();
                ImageView perso;
                TextView name_click;
                perso = findViewById(R.id.perso);
                name_click = findViewById(R.id.name_click);

                name_click.setText(character.getName());

                Glide.with(MainActivity.this)
                        .load(character.getAssets().getPortrait().getPx113())
                        //.apply(RequestOptions.circleCropTransform())
                        .into(perso);*/
            }
        }));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}