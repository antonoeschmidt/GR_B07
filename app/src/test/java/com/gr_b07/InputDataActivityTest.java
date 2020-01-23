package com.gr_b07;

import com.gr_b07.logik.Experience;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.PersonalInfo;
import com.gr_b07.logik.Physique;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Reward;
import com.gr_b07.logik.Settings;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InputDataActivityTest {

    private List<Meal> meals = new ArrayList<>();
    private List<String> friends = new ArrayList<>();
    private List<String> activities = new ArrayList<>();
    private List<Reward> rewards = new ArrayList<>();

    @Before
    public void setUp() {
        meals.add(new Meal("Jordbær", 200,100,50,25,"Breakfast", 1579695267));
        friends.add("Jørgen");
        activities.add("Fodbold");
        rewards.add(new Reward("Æble", 1, R.drawable.apple));

        Pupil pupil = new Pupil(true, "test@dtu.dk", "123123", "zajhdsakgfas", new Physique(195,81,3),
                new PersonalInfo("Ole", "Olesen", "male", 912470400, 2450),
                new Experience(5, 2, 4, 8, 3, false, false, false, false),
                meals, friends, activities, rewards);
        Settings.setCurrentPupil(pupil);
    }

    @Test
    public void testPupilObject() {
        setUp();
        assertEquals(true, Settings.getCurrentPupil().isFirstTimeLoggedIn());
        assertEquals("test@dtu.dk", Settings.getCurrentPupil().getUsername());
        assertEquals("123123", Settings.getCurrentPupil().getPassword());
        assertEquals("zajhdsakgfas", Settings.getCurrentPupil().getUID());
        assertEquals(195, (int) Settings.getCurrentPupil().getPhysique().getHeight());
        assertEquals(81, (int) Settings.getCurrentPupil().getPhysique().getWeight());
        assertEquals(3, Settings.getCurrentPupil().getPhysique().getActivityLevel());
        assertEquals("Ole", Settings.getCurrentPupil().getPersonalInfo().getFirstName());
        assertEquals("Olesen", Settings.getCurrentPupil().getPersonalInfo().getLastName());
        assertEquals("male", Settings.getCurrentPupil().getPersonalInfo().getGender());
        assertEquals(912470400, Settings.getCurrentPupil().getPersonalInfo().getDateOfBirth());
        assertEquals(2450, Settings.getCurrentPupil().getPersonalInfo().getZipCode());
        assertEquals(5, Settings.getCurrentPupil().getExperience().getLevel());
        assertEquals(2, Settings.getCurrentPupil().getExperience().getNutritionXP());
        assertEquals(4, Settings.getCurrentPupil().getExperience().getActivityXP());
        assertEquals(8, Settings.getCurrentPupil().getExperience().getSocialXP());
        assertEquals(3, Settings.getCurrentPupil().getExperience().getTicket());
        assertEquals(false, Settings.getCurrentPupil().getExperience().isXPForCalories());
        assertEquals(false, Settings.getCurrentPupil().getExperience().isXPForProtein());
        assertEquals(false, Settings.getCurrentPupil().getExperience().isXPForCarbs());
        assertEquals(false, Settings.getCurrentPupil().getExperience().isXPForFat());
        assertEquals("Jordbær", Settings.getCurrentPupil().getMeals().get(0).getName());
        assertEquals(200, (int) Settings.getCurrentPupil().getMeals().get(0).getCalories());
        assertEquals(100, (int) Settings.getCurrentPupil().getMeals().get(0).getProtein());
        assertEquals(50, (int) Settings.getCurrentPupil().getMeals().get(0).getCarbs());
        assertEquals(25, (int) Settings.getCurrentPupil().getMeals().get(0).getFat());
        assertEquals("Jørgen", Settings.getCurrentPupil().getFriends().get(0));
        assertEquals("Breakfast", Settings.getCurrentPupil().getMeals().get(0).getTypeOfMeal());
        assertEquals("Fodbold", Settings.getCurrentPupil().getActivities().get(0));
        assertEquals("Æble", Settings.getCurrentPupil().getRewards().get(0).getName());
        assertEquals(1, Settings.getCurrentPupil().getRewards().get(0).getTier());
        assertEquals(R.drawable.apple, Settings.getCurrentPupil().getRewards().get(0).getResource());
    }

    @Test
    public void addMealTest() {
        assertEquals(meals.size(), 1);
        meals.add(new Meal("Banan", 200,100,50,25,"Breakfast", 1579695267));
        assertEquals(meals.size(), 2);
    }


}