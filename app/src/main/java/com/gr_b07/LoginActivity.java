package com.gr_b07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gr_b07.logik.Experience;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.SpringClient;
import com.gr_b07.logik.User;

import java.io.IOException;
import java.util.Date;

import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private ImageView imageViewUser;
    private ImageView imageViewPass;
    private Button loginButton, signUpButton;
    private ProgressDialog progressDialog;
    private FB fb = new FB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Trying HTTP Get with /getallusers
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://35.246.214.109:8080/getallusers";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (fb.getAuth().getCurrentUser() != null) {
            fb.getAuth().signOut();
        }

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Sentry.init("https://ae47774e314c4b79a5b3eff0ec2dbecb@sentry.io/1895656", new AndroidSentryClientFactory(this));
            Sentry.capture(this.getClass().getSimpleName());
        }

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logger ind");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                //der kommer android.view.WindowLeak her, men det har ingen betydning for appen og den crasher ikke

                if (usernameText.getText().toString().equals("admin") && passwordText.getText().toString().equals("admin")) {
                    //adminlogin
                } else {
                    if (!usernameText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty()) {
                        progressDialog.show();
                        fb.firebaseSignIn(progressDialog,this,usernameText.getText().toString(),passwordText.getText().toString());
                    } else {
                        Toast.makeText(this, "Indtast din e-mail og kode", Toast.LENGTH_SHORT).show();
                    }

                }
                break;

            case R.id.signUpButton:
                Intent signUpIntent = new Intent(this, SignUpActivity.class);
                startActivity(signUpIntent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (fb.getAuth().getCurrentUser() != null) {
            usernameText.setText(Settings.getCurrentPupil().getUsername());
            passwordText.setText(Settings.getCurrentPupil().getPassword());
        }
    }
}
