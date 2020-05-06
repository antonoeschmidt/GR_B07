package com.gr_b07.logik;

import android.app.Activity;
import android.app.PictureInPictureParams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gr_b07.logik.HttpRequests.HttpAuthenticateLogInRequest;
import com.gr_b07.logik.HttpRequests.HttpGetAllUsersRequest;
import com.gr_b07.logik.HttpRequests.HttpUpdateDatabaseRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SpringClient {

    private String baseUrl = "http://10.0.2.2:8080";
    //private String baseUrl = "http://35.246.214.109:8080";
    private Activity activity;

    public SpringClient(Activity activity) {
        this.activity = activity;
    }


    /**
     *Gets all users
     * @return
     */
    public String getAllUsers() {

        String myUrl = baseUrl + "/getallusers";
        String result = "";
        HttpGetAllUsersRequest getRequest = new HttpGetAllUsersRequest();

        try {
            result = getRequest.execute(myUrl, "").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Authenticates user email and password
     * @param username
     * @param password
     */
    public String authenticateLogIn(String username, String password) {
        String myUrl = baseUrl + "/androidlogin";
        String result = "";
        String body = "{\"user\":\"" + username + "\",\"pass\":\"" + password + "\"" + "}";
        System.out.println(body);
        HttpAuthenticateLogInRequest logInRequest = new HttpAuthenticateLogInRequest();
        try {
            result = logInRequest.execute(myUrl, body).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before");
        System.out.println(result);
        System.out.println("after");

        return result;
    }

    /**
     * Gets a specific user from a UID, this is used after the authentication has been made
     * @param uid
     * @return
     */
    public Pupil getUser(String uid){
        Pupil pupil = new Pupil();
        String myUrl = baseUrl + "/getuser";
        String result = "";
        HttpGetAllUsersRequest getRequest = new HttpGetAllUsersRequest();

        try {
            result = getRequest.execute(myUrl, uid).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            pupil = JSONtoPupil(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pupil;
    }

    /**
     * Just as in backend, hardcoded the json that is returned to a pupil object
     * @param
     * @throws JSONException
     */
    public Pupil JSONtoPupil(String s) throws JSONException{

        JSONObject json = new JSONObject(s);

        System.out.println(json.toString());

        Pupil pupil = new Pupil();

        pupil.setUsername(json.getString("username"));
        pupil.setPassword(json.getString("password"));
        pupil.setUID(json.getString("uid"));
        pupil.setFirstTimeLoggedIn(json.getBoolean("firstTimeLoggedIn"));

        //Kunne ikke få ting fra nested json objekter så lavede et nyt
        Physique physique = new Physique();
        JSONObject jsonObjectPhysique = json.getJSONObject("physique");
        physique.setHeight(jsonObjectPhysique.getDouble("height"));
        physique.setWeight(jsonObjectPhysique.getDouble("weight"));
        physique.setActivityLevel(jsonObjectPhysique.getInt("activityLevel"));
        pupil.setPhysique(physique);

        PersonalInfo personalInfo = new PersonalInfo();
        JSONObject jsonObjectPersonalInfo = json.getJSONObject("personalInfo");
        personalInfo.setFirstName(jsonObjectPersonalInfo.getString("firstName"));
        personalInfo.setLastName(jsonObjectPersonalInfo.getString("lastName"));
        personalInfo.setGender(jsonObjectPersonalInfo.getString("gender"));
        personalInfo.setDateOfBirth(jsonObjectPersonalInfo.getLong("dateOfBirth"));
        personalInfo.setZipCode(jsonObjectPersonalInfo.getInt("zipCode"));
        pupil.setPersonalInfo(personalInfo);

        Experience experience = new Experience();
        JSONObject jsonObjectExperience = json.getJSONObject("experience");
        experience.setLevel(jsonObjectExperience.getInt("level"));
        experience.setNutritionXP(jsonObjectExperience.getInt("nutritionXP"));
        experience.setActivityXP(jsonObjectExperience.getInt("activityXP"));
        experience.setSocialXP(jsonObjectExperience.getInt("socialXP"));
        experience.setTicket(jsonObjectExperience.getInt("ticket"));
        experience.setXPForCalories(jsonObjectExperience.getBoolean("xpforCalories"));
        experience.setXPForProtein(jsonObjectExperience.getBoolean("xpforProtein"));
        experience.setXPForCarbs(jsonObjectExperience.getBoolean("xpforCarbs"));
        experience.setXPForFat(jsonObjectExperience.getBoolean("xpforFat"));
        pupil.setExperience(experience);

        List<Meal> meals = new ArrayList<>();
        JSONArray mealsJsonArray = new JSONArray(json.get("meals"));
        System.out.println("This should " + mealsJsonArray + " be empty");
        for(int i = 0; i > mealsJsonArray.length(); i++){
            meals.add((Meal) mealsJsonArray.get(i));
        }

        return pupil;
    }

    public void updateDatabase(){
        //Pupil to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(Settings.getCurrentPupil());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);

        //Request itself

        String myUrl = baseUrl + "/saveuser";
        String result = "";
        HttpUpdateDatabaseRequest httpUpdateDatabaseRequest = new HttpUpdateDatabaseRequest();
        try {
            result = httpUpdateDatabaseRequest.execute(myUrl, json).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before");
        System.out.println(result);
        System.out.println("after");
    }

}




        /*final CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(activity);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + "/getallusers",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                System.out.println("Response is: " + response.substring(0, 500));
                                allUsersString = response.substring(0, 500);
                                latch.countDown();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("That didn't work!");
                        allUsersString = "That didn't work";
                        latch.countDown();
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);
                System.out.println(allUsersString);
            }
        });
        thread.start();
        latch.await();

        return allUsersString;*/
