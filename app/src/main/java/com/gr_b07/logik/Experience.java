package com.gr_b07.logik;

import java.util.Set;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNutritionXP() {
        return nutritionXP;
    }

    public void setNutritionXP(int nutritionXP) {
        this.nutritionXP = nutritionXP;
        if (nutritionXP >= 0 && activityXP >= 0 && socialXP >= 0 && level > 0) {
            checkUpdatedX(0);
        }
    }

    public int getActivityXP() {
        return activityXP;
    }

    public void setActivityXP(int activityXP) {
        this.activityXP = activityXP;
        if (nutritionXP >= 0 && activityXP >= 0 && socialXP >= 0 && level > 0) {
            checkUpdatedX(1);
        }
    }

    public int getSocialXP() {
        return socialXP;
    }

    public void setSocialXP(int socialXP) {
        this.socialXP = socialXP;
        if (nutritionXP >= 0 && activityXP >= 0 && socialXP >= 0 && level > 0) {
            checkUpdatedX(2);
        }
    }

    public int getTotalXP() {
        int totalXP = nutritionXP + activityXP + socialXP;
        return totalXP;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public void checkUpdatedX(int xpType) {
        if (getTotalXP() >= level * 5 + 10) {
            if (xpType == 0) {
                Settings.getCurrentPupil().getExperience().setNutritionXP(Settings.getCurrentPupil().getExperience().getTotalXP() % (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
                Settings.getCurrentPupil().getExperience().setActivityXP(0);
                Settings.getCurrentPupil().getExperience().setSocialXP(0);
            }
            if (xpType == 1) {
                Settings.getCurrentPupil().getExperience().setActivityXP(Settings.getCurrentPupil().getExperience().getTotalXP() % (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
                Settings.getCurrentPupil().getExperience().setNutritionXP(0);
                Settings.getCurrentPupil().getExperience().setSocialXP(0);
            }
            if (xpType == 2) {
                Settings.getCurrentPupil().getExperience().setSocialXP(Settings.getCurrentPupil().getExperience().getTotalXP() % (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
                Settings.getCurrentPupil().getExperience().setNutritionXP(0);
                Settings.getCurrentPupil().getExperience().setActivityXP(0);
            }
            Settings.getCurrentPupil().getExperience().setLevel(Settings.getCurrentPupil().getExperience().getLevel() + 1);
            Settings.getCurrentPupil().getExperience().setTicket(Settings.getCurrentPupil().getExperience().getTicket() + 1);
        }
    }
}


