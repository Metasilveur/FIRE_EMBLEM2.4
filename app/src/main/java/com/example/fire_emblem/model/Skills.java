package com.example.fire_emblem.model;

import java.io.Serializable;

public class Skills implements Serializable {
    public String name;
    public String effect;
    public String sp;

    public Skills(String name, String effect, String sp) {
        this.name = name;
        this.effect = effect;
        this.sp = sp;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public String getSp() {
        return sp;
    }
}
