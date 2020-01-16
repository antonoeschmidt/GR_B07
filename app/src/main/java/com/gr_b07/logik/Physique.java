package com.gr_b07.logik;

import java.util.Date;

public class Physique {
    private double  height, weight;
    private int activityLevel;

    public Physique(double height, double weight, int activityLevel) {
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getBMI() {
        System.out.println(weight/Math.pow(height/100,2));
        return weight/Math.pow(height/100,2);
    }
}
