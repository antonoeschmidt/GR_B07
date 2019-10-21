package com.gr_b07;

import android.widget.Toast;

import java.util.ArrayList;

public class LoginLogic {
    private ArrayList<User> users;
    private LoginActivity loginActivity;
    private User currentUser;
    private boolean loggedIn = false;
    private User user1;
    private User user2;

    public void mockUp() {
        users = new ArrayList<>();
        user1 = new User("anton","1234");
        user2 = new User("harald", "4321");
        users.add(user1);
        users.add(user2);

    }

    public void login(String username, String password) {
        mockUp();
        //lidt fucked up det her, men tester
        for (User user: users
             ) {
            if (user.getUsername().equals(username)) {
                currentUser = user;
            }
            if (password.equals(currentUser.getPassword())) {
                loggedIn = true;
            }
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

}
