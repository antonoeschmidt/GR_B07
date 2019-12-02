package com.gr_b07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private ImageView imageViewUser;
    private ImageView imageViewPass;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        imageViewUser = findViewById(R.id.imageViewLogin);
        imageViewPass = findViewById(R.id.imageViewPassword);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        // TODO: DELETE THIS

        usernameText.setText("anton");
        passwordText.setText("1234");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                if (login(usernameText.getText().toString()
                        ,passwordText.getText().toString())) {
                    Toast.makeText(this, "Login virker", Toast.LENGTH_SHORT).show();
                    Intent inputDataIntent = new Intent(this, InputDataActivity.class);
                    startActivity(inputDataIntent);
                } else {
                    Toast.makeText(this, "Forkert login", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public boolean login(String username, String password) {
        for (User user : Settings.getUsers()
        ) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setLoggedIn(true); //kan evt. slettes
                Settings.setCurrentUser(user);
                if (user.getClass().equals(Pupil.class)) {
                    Settings.setCurrentPupil((Pupil)user);
                }
                return user.isLoggedIn();
            }
        }
        return false;
    }

}
