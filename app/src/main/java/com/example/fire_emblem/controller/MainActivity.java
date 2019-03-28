package com.example.fire_emblem.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fire_emblem.R;
import com.example.fire_emblem.model.Skills;
import com.example.fire_emblem.model.Character;
import com.example.fire_emblem.view.CharacterAdapter;
import com.example.fire_emblem.view.RecyclerTouchListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity {

    private List<Character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CharacterAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;

    public final static String TEST = "ECHEC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this);
        controller.onCreate();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void showList(final List<Character> listCharacter) {

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        mAdapter = new CharacterAdapter(this, listCharacter);

        Spinner spinner = findViewById(R.id.spin_choice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.move_type, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(TEST, item.toString());
                editor.commit();

                for(Character perso : listCharacter)
                {
                    if(perso.getMoveType().equals(item.toString())) {
                        perso.select = true;
                        mAdapter.notifyDataSetChanged();
                    }
                    else if(item.toString().equals("All"))
                    {
                        perso.select = true;
                        mAdapter.notifyDataSetChanged();
                    }
                    else{
                        perso.select = false;
                        mAdapter.notifyDataSetChanged();
                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Character character = listCharacter.get(position);
                ImageView perso;
                TextView name_click;
                perso = findViewById(R.id.perso);
                name_click = findViewById(R.id.name_click);

                name_click.setText(character.getShortName());
                //name_click.setText(preferences.getString(TEST, "WESH"));
                name_click.setTextColor(BLACK);

                Glide.with(MainActivity.this)
                        .load(character.getAssets().getPortrait().getPx113())
                        //.apply(RequestOptions.circleCropTransform())
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
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}