package com.gr_b07.logik;

public abstract class User {
    private String username, password;
    private boolean firstTimeLoggedIn;

    public User(boolean firstTimeLoggedIn, String username, String password) {
        this.firstTimeLoggedIn = firstTimeLoggedIn;
        this.username = username;
        this.password = password;
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

}

