package com.example.fire_emblem;

public class Assets {
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
