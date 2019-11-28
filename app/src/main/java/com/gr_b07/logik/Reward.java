package com.gr_b07.logik;

import android.content.res.Resources;

public class Reward {
    private String name;
    private int tier;
    private String resource;

    public Reward(String name, int tier, String resource){
        this.name = name;
        this.tier = tier;
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
