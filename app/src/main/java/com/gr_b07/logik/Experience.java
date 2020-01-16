package com.gr_b07.logik;

public class Experience {
    private int level, nutritionXP, activityXP, socialXP, ticket;

    public Experience(int level, int nutritionXP, int activityXP, int socialXP, int ticket) {
        this.level = level;
        this.nutritionXP = nutritionXP;
        this.activityXP = activityXP;
        this.socialXP = socialXP;
        this.ticket = ticket;
    }

    public Experience() {
        //røv vigtigt
    }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getNutritionXP() {
        return nutritionXP;
    }

    public void setNutritionXP(int nutritionXP) {
        this.nutritionXP = nutritionXP;
    }

    public int getActivityXP() {
        return activityXP;
    }

    public void setActivityXP(int activityXP) {
        this.activityXP = activityXP;
    }

    public int getSocialXP() {
        return socialXP;
    }

    public void setSocialXP(int socialXP) {
        this.socialXP = socialXP;
    }

    public int getTotalXP(){
        int totalXP = nutritionXP + activityXP + socialXP;
        return totalXP;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
}
