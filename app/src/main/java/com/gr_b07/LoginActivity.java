package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginLogic loginLogic;
    private EditText usernameText;
    private EditText passwordText;
    private ImageView imageViewUser;
    private ImageView imageViewPass;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginLogic = new LoginLogic();

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        imageViewUser = findViewById(R.id.imageViewLogin);
        imageViewPass = findViewById(R.id.imageViewPassword);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        loginLogic.mockUp();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                if (loginLogic.login(usernameText.getText().toString()
                        ,passwordText.getText().toString())) {
                    Toast.makeText(this, "Login virker", Toast.LENGTH_SHORT).show();;
                    Intent inputDataIntent = new Intent(this, InputDataActivity.class);
                    startActivity(inputDataIntent);
                } else {
                    Toast.makeText(this, "Forkert login", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
