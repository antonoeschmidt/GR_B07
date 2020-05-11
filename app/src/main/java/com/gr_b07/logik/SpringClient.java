package com.gr_b07.logik;

import android.app.Activity;
import android.app.PictureInPictureParams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.gr_b07.logik.HttpRequests.HttpAuthenticateLogInRequest;
import com.gr_b07.logik.HttpRequests.HttpCreateUserRequest;
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

    private String baseUrl = "http://35.246.214.109:8080";
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
    public String authenticateLogIn(String username, String password){
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
        String myUrl = baseUrl + "/getuserandroid";
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

    public String createUser(Pupil user){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        String myUrl = baseUrl + "/createuser";
        String result = "";
        HttpCreateUserRequest httpCreateUserRequest = new HttpCreateUserRequest();
        try {
            result = httpCreateUserRequest.execute(myUrl, json).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("before");
        System.out.println(result);
        System.out.println("after");

        return result;

    }

    /**
     * Just as in backend, hardcoded the json that is returned to a pupil object
     * @param
     * @throws JSONException
     */
    public Pupil JSONtoPupil(String s) throws JSONException{

        System.out.println(s);

        JSONObject json = new JSONObject(s);
        System.out.println(json);
        Gson g = new Gson();
        Pupil p = g.fromJson(json.toString(), Pupil.class);
        p.setUID(json.getString("uid"));
        return p;
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
