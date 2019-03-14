package com.example.fire_emblem;

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
