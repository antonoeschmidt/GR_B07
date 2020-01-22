package com.gr_b07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.FB;

import java.time.LocalDateTime;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private ImageView imageViewUser;
    private ImageView imageViewPass;
    private Button signUpButton;
    private ProgressDialog progressDialog;
    private FB fb = new FB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        imageViewUser = findViewById(R.id.imageViewLogin);
        imageViewPass = findViewById(R.id.imageViewPassword);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Opretter bruger");

        // TODO: DELETE THIS

        usernameText.setText("healthy1@gmail.com");
        passwordText.setText("123123");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpButton:
                progressDialog.show();
                final String email = usernameText.getText().toString();
                final String password = passwordText.getText().toString();
                fb.signUp(progressDialog,this, email,password);
                break;
        }
    }
}
