package com.gr_b07;

import java.time.LocalDate;
import java.util.Date;

public abstract class User {
    private String username, password;
    private char gender;
    private boolean loggedIn;

    public User(String username, String password, char gender, boolean loggedIn) {
        this.username = username;
        this.password = password;
        this.gender = gender;
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

     public char getGender() {
         return gender;
     }

     public void setGender(char gender) {
         this.gender = gender;
     }

     public abstract double getHeight();
     public abstract void setHeight(double height);
     public abstract double getWeight();
     public abstract void setWeight(double weight);
     public abstract double getBmi();
     public abstract void setBmi(double bmi);
     public abstract double getCalories();
     public abstract void setCalories(double calories);
     public abstract double getProtein();
     public abstract void setProtein(double protein);
     public abstract double getCarbs();
     public abstract void setCarbs(double carbs);
     public abstract double getFat();
     public abstract void setFat(double fat);
     public abstract Date getDateOfBirth();
     public abstract void setDateOfBirth(Date dateOfBirth);
     public abstract int getAge();
     public abstract void setAge(int age);


 }
