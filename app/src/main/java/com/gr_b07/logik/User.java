package com.gr_b07.logik;

import java.util.Date;

public abstract class User {
    private String username, password;
    private boolean loggedIn, firstTimeLoggedIn;

    public User(boolean firstTimeLoggedIn, String username, String password, boolean loggedIn) {
        this.firstTimeLoggedIn = firstTimeLoggedIn;
        this.username = username;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public User() {

    }

    public boolean isFirstTimeLoggedIn() {
        return firstTimeLoggedIn;
    }

    public void setFirstTimeLoggedIn(boolean firstTimeLoggedIn) {
        this.firstTimeLoggedIn = firstTimeLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    //TODO: abstrakte metoder slettes

     }

