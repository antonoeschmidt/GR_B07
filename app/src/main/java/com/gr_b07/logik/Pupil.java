package com.gr_b07.logik;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pupil extends User {

    private Physique physique;
    private PersonalInfo personalInfo;
    private Experience experience;
    private String UID;

    //TODO: evt fjern new ArrayList
    private List<Meal> meals = new ArrayList<>();
    private List<String> friends = new ArrayList<>();
    private List<String> activities = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();

    public Pupil(boolean firstTimeLoggedIn, String username, String password, boolean loggedIn, String UID, Physique physique,
                 PersonalInfo personalInfo, Experience experience, List<Meal> meals, List<String> friends,
                 List<String> activities, List<Reward> rewards) {
        super(firstTimeLoggedIn, username, password, loggedIn, UID);
        this.physique = physique;
        this.personalInfo = personalInfo;
        this.experience = experience;
        this.meals = meals;
        this.friends = friends;
        this.activities = activities;
        this.rewards = rewards;
        this.UID = UID;

    }

    public Pupil() {
        //røv vigtig
    }

    public Food getDailyIntake(long date) {
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date(System.currentTimeMillis());
        String dateString = f1.format(date);
        String nowString = f1.format(now);
        int calories = 0, protein = 0, carbs = 0, fat = 0;

        for (Meal meal :
                meals) {
            if (nowString.equals(dateString)) {
                calories += meal.getCalories();
                protein += meal.getProtein();
                carbs += meal.getCarbs();
                fat += meal.getFat();
            }
        }

        return new Food("dailyIntake", calories, protein, carbs, fat);
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void addReward(Reward reward) {
        rewards.add(reward);
    }

    public void addFriend(String UID) { friends.add(UID); }

    public void addActivity(String activity) {
        activities.add(activity);
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

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}

