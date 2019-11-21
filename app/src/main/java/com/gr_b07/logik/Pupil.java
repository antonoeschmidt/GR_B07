package com.gr_b07.logik;

import java.util.Date;

public class Pupil extends User {

    private double height, weight, bmi, calories, protein, carbs, fat;
    private Date dateOfBirth;
    private int age;
    private Experience experience;

    public Pupil(String username, String password, char gender, boolean loggedIn, double height,
                 double weight, double bmi, double calories, double protein, double carbs, double fat,
                 Date dateOfBirth, int age, Experience experience) {
        super(username, password,gender, loggedIn);
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.experience = experience;
    }

    public Experience getExperience() {
        return experience;
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

    public double getCalories() { return calories; }

    public void setCalories(double calories) { this.calories = calories; }

    public double getProtein() { return protein; }

    public void setProtein(double protein) { this.protein = protein; }

    public double getCarbs() { return carbs; }

    public void setCarbs(double carbs) { this.carbs = carbs; }

    public double getFat() { return fat; }

    public void setFat(double fat) { this.fat = fat; }

    @Override
    public Date getDateOfBirth() { return dateOfBirth; }

    @Override
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }
}

