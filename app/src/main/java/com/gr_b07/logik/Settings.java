package com.gr_b07.logik;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;

public class Settings {

    private static ArrayList<User> users = new ArrayList<User>() {
        {
            add(new Pupil("anton", "1234", 'n', false,
                    182, 70, 0, 0, 0, 0, 0,
                    null, 0, new Experience(1,0),0));


            add(new Pupil("harald", "4321", 'n', false,
                    183, 75, 0, 0, 0, 0, 0,
                    null, 0,new Experience(1,0),0));
            // TODO : REMOVE
        }
    };
    private static User currentUser;

    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() { return currentUser; }

    public static void setCurrentUser(User currentUser) { Settings.currentUser = currentUser; }

    public static void setUsers(ArrayList<User> users) {Settings.users = users;}

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}

