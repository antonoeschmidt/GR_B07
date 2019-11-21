package com.gr_b07.logik;

public class Experience {
    private int level, xp, range;

    public Experience(int level, int xp) {
        this.level = level;
        this.xp = xp;
        this.range = 10+5*level;
    }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getXp() { return xp; }

    public void setXp(int xp) { this.xp = xp; }

    public int getRange() { return range; }

    public void setRange(int range) { this.range = range; }

    public void fixRange (){
        range = 10+5*level;
    }
}
