package com.example.fire_emblem;

import java.util.List;

public class Character {
    public String name;
    public String shortName;
    public String moveType;
    public String weaponType;
    public Growths growths;
    public Assets assets;
    public String origin;
    public List<Skills> skills;
    public String rarities;
    public boolean select=true;

    public boolean isSelect() {
        return select;
    }

    public String getRarities() {
        return rarities;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    /*public Character(String name, String moveType, String weaponType) {
        this.name = name;
        this.moveType = moveType;
        this.weaponType = weaponType;
    }*/

    public String getOrigin() {
        return origin;
    }

    public Assets getAssets() { return assets; }

    public Growths getGrowths() { return growths; }

    public String getShortName() { return shortName; }

    public String getName() {
        return name;
    }

    public String getMoveType() {
        return moveType;
    }

    public String getWeaponType() {
        return weaponType;
    }
}
