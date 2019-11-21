package com.gr_b07.logik;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

public class Settings {
    private static final Level[] levels = {new Level(10, 1), new Level(20, 2), new Level(30, 3), new Level(40, 4), new Level(50, 5)};

    private static ArrayList<User> users = new ArrayList<User>() {
        {
            add(new Pupil("anton", "1234", 'n', false, 182, 70, 0, 0, 0, 0, 0, null, 0,new Experience(0, levels[0], levels)));
            add(new Pupil("harald", "4321", 'n', false, 183, 75, 0, 0, 0, 0, 0, null, 0,new Experience(0, levels[0], levels)));
            levels[0].setIndex(1);
            levels[0].setRange(100);
        }
    };
    private static User currentUser;

    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Settings.currentUser = currentUser;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}

