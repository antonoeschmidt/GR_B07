package com.gr_b07.logik;

public class Level {
    private int range;
    private int index;

    public Level(int range, int index) {
        this.range = range;
        this.index = index;
    }

    public int getRange() {
        return range;
    }
    public void setRange(int range) { this.range = range; }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) { this.index = index; }
}
