package com.gr_b07;

 public abstract class User {
    private String username;
    private String password;
    private boolean loggedIn;

    public User(String username, String password, boolean loggedIn) {
        this.username = username;
        this.password = password;
        this.loggedIn = loggedIn;
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

     public abstract double getHeight();
     public abstract void setHeight(double height);
     public abstract double getWeight();
     public abstract void setWeight(double weight);
     public abstract double getBmi();
     public abstract void setBmi(double bmi);


}
