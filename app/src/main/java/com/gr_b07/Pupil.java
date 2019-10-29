package com.gr_b07;

public class Pupil extends User {

    private double height, weight, bmi;

    public Pupil(String username,String password,boolean loggedIn, double height, double weight, double bmi) {
        super(username, password, loggedIn);
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
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

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}

