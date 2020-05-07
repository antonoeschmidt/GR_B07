package com.gr_b07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.SpringClient;


import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private ImageView imageViewUser;
    private ImageView imageViewPass;
    private Button loginButton, signUpButton;
    private ProgressDialog progressDialog;
    private SpringClient springClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*String allUsers = "";
        SpringClient springClient = new SpringClient(this);
        try {
            allUsers = springClient.getAllUsers();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("This is " + allUsers + "All users");
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        springClient = new SpringClient(this);

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
                progressDialog.show();
                if (usernameText.getText().toString().equals("admin") && passwordText.getText().toString().equals("admin")) {
                    //adminlogin
                } else {
                    if (!usernameText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty()) {
                        String authorisedUID = springClient.authenticateLogIn(usernameText.getText().toString(),
                                passwordText.getText().toString());
                        if (!authorisedUID.equals("f")) {
                            Toast.makeText(this, "WE IN BABY", Toast.LENGTH_SHORT).show();
                            Pupil user = springClient.getUser(authorisedUID);
                            Settings.setCurrentUser(user);
                            //this will handle if someone other than pupil logs in ex. admin
                            //this needs to be implimented
                            if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                                Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                            }
                            if (Settings.getCurrentUser().isFirstTimeLoggedIn()) {
                                Intent inputDataIntent = new Intent(this, InputDataActivity.class);
                                startActivity(inputDataIntent);
                            } else  {
                                Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
                                startActivity(mainMenuIntent);
                            }

                        } else {
                            Toast.makeText(this, "NOT", Toast.LENGTH_SHORT).show();

                        }
                        // fb.firebaseSignIn(progressDialog,this,usernameText.getText().toString(),passwordText.getText().toString());
                    } else {
                        Toast.makeText(this, "Indtast din e-mail og kode", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.hide();
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

        /*
        if (fb.getAuth().getCurrentUser() != null) {
            usernameText.setText(Settings.getCurrentPupil().getUsername());
            passwordText.setText(Settings.getCurrentPupil().getPassword());
        }
        */

    }
}
