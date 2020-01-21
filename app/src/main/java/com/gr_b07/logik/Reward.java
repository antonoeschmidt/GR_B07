package com.gr_b07.logik;

import android.content.res.Resources;

public class Reward {
    private String name;
    private int tier;
    private int resource;
    private boolean redeemed;

    public Reward(String name, int tier, int resource){
        this.name = name;
        this.tier = tier;
        this.resource = resource;
        this.redeemed = false;
    }

    public Reward() {

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

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void redeem(){
        redeemed = true;
    }

}
