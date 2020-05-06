package com.gr_b07.logik;

public abstract class User {
    private String username, password, UID;
    private boolean firstTimeLoggedIn;

    public User(boolean firstTimeLoggedIn, String username, String password, String UID) {
        this.firstTimeLoggedIn = firstTimeLoggedIn;
        this.username = username;
        this.password = password;
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

