package com.example.fire_emblem.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fire_emblem.model.Battle;
import com.example.fire_emblem.R;

import java.util.List;

import static android.graphics.Color.WHITE;

public class BattleAdapter extends RecyclerView.Adapter<BattleAdapter.MyViewHolder> {

    private List<Battle> battleList;
    private Context context;

    public BattleAdapter(Context context, List<Battle> battleList)
    {
        this.context = context;
        this.battleList = battleList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fighter2_name,fighter1_name, score1, score2, score1_txt, score2_txt;
        public ImageView fighter1, fighter2;
        public  TextView turn;

        public MyViewHolder(View view) {
            super(view);
            fighter2_name = (TextView) view.findViewById(R.id.fighter2_name);
            fighter1_name = (TextView) view.findViewById(R.id.fighter1_name);
            score1 = (TextView) view.findViewById(R.id.score1);
            score2 = (TextView) view.findViewById(R.id.score2);
            score1_txt = (TextView) view.findViewById(R.id.score1_txt);
            score2_txt = (TextView) view.findViewById(R.id.score2_txt);
            fighter1 = view.findViewById(R.id.fighter1);
            fighter2 = view.findViewById(R.id.fighter2);
            turn = view.findViewById(R.id.turn);
        }
    }

    @Override
    public BattleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.battle_list, parent, false);

        return new BattleAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Battle battle = battleList.get(position);

        holder.fighter2_name.setText(battle.getCharacter2().getShortName());
        holder.fighter1_name.setText(battle.getCharacter1().getShortName());

        holder.score1.setText(battle.getScore1());
        holder.score2.setText(battle.getScore2());

        holder.score1_txt.setText(battle.getScore1_txt());
        holder.score2_txt.setText(battle.getScore2_txt());

        holder.fighter1_name.setTextColor(WHITE);
        holder.fighter2_name.setTextColor(WHITE);

        holder.score1.setTextColor(WHITE);
        holder.score2.setTextColor(WHITE);

        holder.score1_txt.setTextColor(WHITE);
        holder.score2_txt.setTextColor(WHITE);

        holder.turn.setText(battle.getTurn());
        holder.turn.setTextColor(WHITE);

        Glide.with(context)
                .load(battle.getCharacter1().getAssets().getPortrait().getPx113())
                //.apply(RequestOptions.circleCropTransform())
                .into(holder.fighter1);


        Glide.with(context)
                .load(battle.getCharacter2().getAssets().getPortrait().getPx113())
                //.apply(RequestOptions.circleCropTransform())
                .into(holder.fighter2);
    }

    @Override
    public int getItemCount() {
        return battleList.size();
    }

}
