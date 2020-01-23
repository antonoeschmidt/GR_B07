package com.gr_b07;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.gr_b07.logik.User;

import java.util.Date;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (fb.getAuth().getCurrentUser() != null) {
            fb.getAuth().signOut();
        }

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logger ind");

        // TODO: DELETE THIS

        usernameText.setText("healthy14@gmail.com");
        passwordText.setText("123123");
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
                    fb.firebaseSignIn(progressDialog,this,usernameText.getText().toString(),passwordText.getText().toString());
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
