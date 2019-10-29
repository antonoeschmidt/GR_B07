package com.gr_b07;

import java.util.ArrayList;

public class Settings {
    private static ArrayList<User> users = new ArrayList<User>() {
        {
            add(new Pupil("anton", "1234",false,0,0,0));
            add(new Pupil("harald", "4321",false,0,0,0));
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
}
