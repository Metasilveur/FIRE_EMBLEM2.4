package com.example.fire_emblem.view;

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
import android.widget.Button;

import com.example.fire_emblem.R;
import com.example.fire_emblem.controller.CharacterAdapter;
import com.example.fire_emblem.controller.SelectController;
import com.example.fire_emblem.model.Character;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectCharacter extends AppCompatActivity {

    private List<Character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CharacterAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SelectController controller;

    private int cpt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_select);

        controller = new SelectController(this);
        controller.onCreate();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_selection);
    }

    public void showList(final List<Character> listCharacter) {

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        for(Character perso : listCharacter)
        {
            perso.select = false;
        }

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        mAdapter = new CharacterAdapter(this, listCharacter);

        Button LeBoutton = (Button) findViewById(R.id.select_charac);

        LeBoutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cpt == 2)
                {
                    Intent MyIntent = new Intent(SelectCharacter.this, BattleActivityDynamic.class);
                    int stop = 0;
                    for(Character perso : listCharacter)
                    {
                        if(perso.select == true)
                        {
                            if(stop == 0)
                            {
                                MyIntent.putExtra("Fight1", (Serializable) perso);
                                stop++;
                            }
                            else{
                                MyIntent.putExtra("Fight2", (Serializable) perso);
                                startActivity(MyIntent);
                            }
                        }
                    }
                }
            }
        });

        recyclerView.setAdapter(mAdapter);



        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Character character = listCharacter.get(position);
                if(!character.select)
                {
                    if(cpt < 2) {
                        character.select = true;
                        cpt++;
                    }
                }
                else{
                    character.select = false;
                    cpt--;
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(View view, int position) {

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