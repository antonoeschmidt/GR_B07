package com.gr_b07.logik;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pupil extends User {

    private Physique physique;
    private PersonalInfo personalInfo;
    private Experience experience;

    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<Pupil> friends = new ArrayList<>();
    private ArrayList<String> activities = new ArrayList<>();
    private ArrayList<Reward> rewards = new ArrayList<>();

    public Pupil(boolean firstTimeLoggedIn, String username, String password, boolean loggedIn, Physique physique,
                 PersonalInfo personalInfo, Experience experience, ArrayList<Meal> meals, ArrayList<Pupil> friends,
                 ArrayList<String> activities, ArrayList<Reward> rewards) {
        super(firstTimeLoggedIn, username, password, loggedIn);
        this.physique = physique;
        this.personalInfo = personalInfo;
        this.experience = experience;
        this.meals = meals;
        this.friends = friends;
        this.activities = activities;
        this.rewards = rewards;
    }

    public Pupil() {
        //røv vigtig
    }

    public Food getDailyIntake(long date) {
        Date newDate = new Date(System.currentTimeMillis());

        for (Meal meal :
                meals) {

            if (date = today) {

            }

        }

        return null;
    }

    public Physique getPhysique() {
        return physique;
    }

    public void setPhysique(Physique physique) {
        this.physique = physique;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Pupil> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Pupil> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<String> activities) {
        this.activities = activities;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<Reward> rewards) {
        this.rewards = rewards;
    }
}

