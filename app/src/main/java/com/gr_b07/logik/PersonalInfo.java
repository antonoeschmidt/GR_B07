package com.gr_b07.logik;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalInfo {
    private String firstName, lastName, gender;
    private long dateOfBirth;
    private int zipCode;

    public PersonalInfo(String firstName, String lastName, String gender, long dateOfBirth, int zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.zipCode = zipCode;
    }

    public PersonalInfo() {

    }


    public int getAge() throws NumberFormatException {
        Date now = new Date(System.currentTimeMillis());

        long timeBetween = now.getTime() - dateOfBirth;
        double yearsBetween = timeBetween / 3.15576e+10;
        int age = (int) Math.floor(yearsBetween);
        return age;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}

