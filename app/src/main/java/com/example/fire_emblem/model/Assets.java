package com.example.fire_emblem.model;

import com.example.fire_emblem.model.Portrait;

import java.io.Serializable;

public class Assets implements Serializable {
    private Portrait portrait;

    public Portrait getPortrait ()
    {
        return portrait;
    }

    public void setPortrait (Portrait portrait)
    {
        this.portrait = portrait;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [portrait = "+portrait+"]";
    }
}
