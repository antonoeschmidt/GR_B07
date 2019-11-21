package com.gr_b07.logik;

public class Experience {
    private int xp;
    private Level currentLevel;
    private Level[] levels;

    public Experience(int xp, Level currentLevel, Level[] levels) {
        this.xp = xp;
        this.currentLevel = currentLevel;
        this.levels = levels;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Level[] getLevels() {
        return levels;
    }

}
