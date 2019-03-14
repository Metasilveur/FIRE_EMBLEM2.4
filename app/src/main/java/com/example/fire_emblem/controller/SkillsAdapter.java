package com.example.fire_emblem.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fire_emblem.R;
import com.example.fire_emblem.Skills;

import java.util.List;

import static android.graphics.Color.WHITE;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.MyViewHolder> {

    private List<Skills> skillsList;
    private Context context;

    public SkillsAdapter(Context context, List<Skills> skillsList)
    {
        this.context = context;
        this.skillsList = skillsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_skill, effect, sp;

        public MyViewHolder(View view) {
            super(view);
            name_skill = (TextView) view.findViewById(R.id.name_skill);
            effect = (TextView) view.findViewById(R.id.effect);
            sp = (TextView) view.findViewById(R.id.sp);
        }
    }

    @Override
    public SkillsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_description, parent, false);

        return new SkillsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Skills skills = skillsList.get(position);

        holder.name_skill.setText(skills.getName());
        holder.effect.setText(skills.getEffect());
        holder.sp.setText(skills.getSp());

        holder.name_skill.setTextColor(WHITE);
        holder.effect.setTextColor(WHITE);
        holder.sp.setTextColor(WHITE);
    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }

}
