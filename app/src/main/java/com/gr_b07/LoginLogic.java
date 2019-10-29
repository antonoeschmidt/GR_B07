package com.gr_b07;

import java.util.ArrayList;

public class LoginLogic {
    private ArrayList<User> users;
    private LoginActivity loginActivity;
    private boolean loggedIn = false;
    private User pupil1;
    private User pupil2;

    public void mockUp() {
        users = new ArrayList<>();
        pupil1 = new Pupil("anton", "1234",false,0,0,0);
        pupil2 = new Pupil("harald", "4321",false,0,0,0);
        users.add(pupil1);
        users.add(pupil2);

    }

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
