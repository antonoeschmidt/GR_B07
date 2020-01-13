package com.gr_b07.logik;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pupil extends User {

    private double height, weight, bmi, calories, protein, carbs, fat;
    private Date dateOfBirth;
    private int age, ticket, activityLevel;
    private Experience experience;
    private String gender;
    private ArrayList<Reward> rewards = new ArrayList<>();

    public Pupil(boolean firstTimeLoggedIn, String username, String password, boolean loggedIn, double height,
                 double weight, double bmi, double calories, double protein, double carbs, double fat,
                 Date dateOfBirth, int age, Experience experience, int ticket, String gender, int activityLevel, ArrayList<Reward> rewards) {
        super(firstTimeLoggedIn, username, password, loggedIn);
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
        this.ticket = ticket;
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.rewards = rewards;
    }
    //setBmi(Settings.getCurrentPupil().getWeight()/ (Math.pow(Settings.getCurrentPupil().getHeight() / 100, 2)));

    public int calculateAge(Date dateOfBirth) throws NumberFormatException, ParseException {
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date(System.currentTimeMillis());

        long timeBetween = now.getTime() - Settings.getCurrentPupil().getDateOfBirth().getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        int age = (int) Math.floor(yearsBetween);
        return age;

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public Experience getExperience() { return experience; }

    public void setExperience(Experience experience) { this.experience = experience; }

    public int getTicket() { return ticket; }

    public void setTicket(int ticket) { this.ticket = ticket; }

    public int getActivityLevel() { return activityLevel; }

    public void setActivityLevel(int activityLevel) {this.activityLevel = activityLevel; }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void addReward(Reward reward){
        rewards.add(reward);
    }

}

