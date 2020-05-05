package com.gr_b07.logik;

import android.app.Activity;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gr_b07.logik.HttpRequests.HttpGetAllUsersRequest;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;
import java.util.logging.Handler;

public class SpringClient {

    private String baseUrl = "http://35.246.214.109:8080", allUsersString;
    private Activity activity;

    public SpringClient(Activity activity) {
        this.activity = activity;
    }

    public String getAllUsers() throws InterruptedException {
        //Some url endpoint that you may have
        String myUrl = baseUrl + "/getallusers";
        //String to place our result in
        String result = "";
        //Instantiate new instance of our class
        HttpGetAllUsersRequest getRequest = new HttpGetAllUsersRequest();
        //Perform the doInBackground method, passing in our url
        try {
            result = getRequest.execute(myUrl).get();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("PENISHEAD" + result);
        return result;
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
