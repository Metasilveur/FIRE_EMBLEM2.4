package com.example.fire_emblem;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {

    private List<Character> characterList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, moveType, total;
        public ImageView image, type, cadre, bordure;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            moveType = (TextView) view.findViewById(R.id.moveType);
            total = (TextView) view.findViewById(R.id.total);
            image = view.findViewById(R.id.image);
            type = view.findViewById(R.id.type);
            cadre = view.findViewById(R.id.cadre);
            bordure = view.findViewById(R.id.bordure);
        }
    }


    public CharacterAdapter(Context context, List<Character> moviesList) {
        this.characterList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Character character = characterList.get(position);
        /*SpannableString spannableString = new SpannableString(character.getShortName());
        spannableString.setSpan(new BackgroundColorSpan(Color.WHITE), 0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.name.setText(spannableString);*/
        //holder.moveType.setText(character.getMoveType());
        //holder.total.setText(character.getWeaponType());
        //holder.type.setImageResource(R.drawable.grey_dragon);
        if(character.getWeaponType().equals("Red Sword"))
        {
            holder.type.setImageResource(R.drawable.red_sword);
        }
        if(character.getWeaponType().equals("Blue Lance"))
        {
            holder.type.setImageResource(R.drawable.blue_lance);
        }
        if(character.getWeaponType().equals("Green Axe"))
        {
            holder.type.setImageResource(R.drawable.green_axe);
        }
        if(character.getWeaponType().equals("Red Bow"))
        {
            holder.type.setImageResource(R.drawable.red_bow);
        }
        if(character.getWeaponType().equals("Blue Bow"))
        {
            holder.type.setImageResource(R.drawable.blue_bow);
        }
        if(character.getWeaponType().equals("Green Bow"))
        {
            holder.type.setImageResource(R.drawable.green_bow);
        }
        if(character.getWeaponType().equals("Colorless Bow"))
        {
            holder.type.setImageResource(R.drawable.grey_bow);
        }
        if(character.getWeaponType().equals("Red Dagger"))
        {
            holder.type.setImageResource(R.drawable.red_ninja);
        }
        if(character.getWeaponType().equals("Blue Dagger"))
        {
            holder.type.setImageResource(R.drawable.blue_ninja);
        }
        if(character.getWeaponType().equals("Colorless Dagger"))
        {
            holder.type.setImageResource(R.drawable.grey_ninja);
        }
        if(character.getWeaponType().equals("Green Dagger"))
        {
            holder.type.setImageResource(R.drawable.green_ninja);
        }
        if(character.getWeaponType().equals("Red Tome"))
        {
            holder.type.setImageResource(R.drawable.red_tome);
        }
        if(character.getWeaponType().equals("Green Tome"))
        {
            holder.type.setImageResource(R.drawable.green_tome);
        }
        if(character.getWeaponType().equals("Blue Tome"))
        {
            holder.type.setImageResource(R.drawable.blue_tome);
        }
        if(character.getWeaponType().equals("Red Breath"))
        {
            holder.type.setImageResource(R.drawable.red_dragon);
        }
        if(character.getWeaponType().equals("Blue Breath"))
        {
            holder.type.setImageResource(R.drawable.blue_dragon);
        }
        if(character.getWeaponType().equals("Green Breath"))
        {
            holder.type.setImageResource(R.drawable.green_dragon);
        }
        if(character.getWeaponType().equals("Colorless Breath"))
        {
            holder.type.setImageResource(R.drawable.grey_dragon);
        }
        if(character.getWeaponType().equals("Colorless Staff"))
        {
            holder.type.setImageResource(R.drawable.grey_staff);
        }

        if(character.getRarities().equals("1-4"))
        {
            holder.cadre.setImageResource(R.drawable.cadre1);
            holder.bordure.setImageResource(R.drawable.bordure1);
        }
        if(character.getRarities().equals("2-4"))
        {
            holder.cadre.setImageResource(R.drawable.cadre2);
            holder.bordure.setImageResource(R.drawable.bordure2);
        }
        if(character.getRarities().equals("3-4"))
        {
            holder.cadre.setImageResource(R.drawable.cadre3);
            holder.bordure.setImageResource(R.drawable.bordure3);
        }
        if(character.getRarities().equals("4"))
        {
            holder.cadre.setImageResource(R.drawable.cadre4);
            holder.bordure.setImageResource(R.drawable.bordure4);
        }
        if(character.getRarities().equals("4-5"))
        {
            holder.cadre.setImageResource(R.drawable.cadre5);
            holder.bordure.setImageResource(R.drawable.bordure5);
        }
        if(character.getRarities().equals("5"))
        {
            holder.cadre.setImageResource(R.drawable.cadre6);
            holder.bordure.setImageResource(R.drawable.bordure6);
        }

        Glide.with(context)
                .load(character.getAssets().getPortrait().getPx75())
                //.apply(RequestOptions.circleCropTransform())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }
}
