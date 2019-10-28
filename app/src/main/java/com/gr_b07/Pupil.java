package com.gr_b07;

public class Pupil extends User {

    private int height, weight, bmi;

    public Pupil(String username,String password,boolean loggedIn, int height, int weight, int bmi) {
        super(username, password, loggedIn);
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBmi() {
        return bmi;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }
}

