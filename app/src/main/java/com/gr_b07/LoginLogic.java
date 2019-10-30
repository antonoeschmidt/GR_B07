package com.gr_b07;

import java.util.ArrayList;

public class LoginLogic {
    private ArrayList<User> users;
    private LoginActivity loginActivity;

    public boolean login(String username, String password) {
        for (User user : users
        ) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setLoggedIn(true);
                return user.isLoggedIn();
            }
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public LoginActivity getLoginActivity() {
        return loginActivity;
    }
}
