package com.gr_b07.logik;

import java.util.Date;

public abstract class User {
    private String username, password, UID;
    private boolean loggedIn, firstTimeLoggedIn;

    public User(boolean firstTimeLoggedIn, String username, String password, boolean loggedIn, String UID) {
        this.firstTimeLoggedIn = firstTimeLoggedIn;
        this.username = username;
        this.password = password;
        this.loggedIn = loggedIn;
        this.UID = UID;
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    //TODO: abstrakte metoder slettes

     }

