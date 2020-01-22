package com.gr_b07.logik;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Settings {

    public static boolean logginIn;
    private static ArrayList<User> users = new ArrayList<User>();
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static User currentUser;
    private static Pupil currentPupil;


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

    public static void setUsers(ArrayList<User> users) {
        Settings.users = users;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static int longToIntDate(long time) {
        Date date = new Date(time);
        String dateString = df.format(date);
        System.out.println(dateString);
        int x = Integer.parseInt(dateString.substring(0, 2));
        return x;
    }

    public static String longToStringDate(long time) {
        Date date = new Date(time);
        System.out.println(df.format(date));
        return df.format(date);
    }

    public static Pupil getCurrentPupil() {
        return currentPupil;
    }

    public static void setCurrentPupil(Pupil currentPupil) {
        Settings.currentPupil = currentPupil;
    }
}

