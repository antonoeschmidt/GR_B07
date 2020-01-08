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

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    //login
                    Toast.makeText(LoginActivity.this,"Det virker", Toast.LENGTH_LONG).show();
                } else {
                    //logout
                }
            }
        };

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        imageViewUser = findViewById(R.id.imageViewLogin);
        imageViewPass = findViewById(R.id.imageViewPassword);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logger ind");

        // TODO: DELETE THIS

        usernameText.setText("nyigen@kk.dk");
        passwordText.setText("nyziyang123");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //FirebaseUser currentUser = auth.getCurrentUser();
        auth.addAuthStateListener(authStateListener);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                /*
                if (usernameText.getText().toString().equals("admin") && passwordText.getText().toString().equals("admin")) {
                    //adminlogin
                } else {
                    firebaseSignIn();
                }
                */
                Log.d("pupil", ""+ Settings.getCurrentPupil());
                Log.d("user", ""+ Settings.getCurrentUser());
                firebaseSignIn();
                Log.d("pupil", ""+ Settings.getCurrentPupil());
                Log.d("user", ""+ Settings.getCurrentUser());







                /*if (login(usernameText.getText().toString()
                        ,passwordText.getText().toString())) {
                    //Toast.makeText(this, "Login virker", Toast.LENGTH_SHORT).show();
                    Intent inputDataIntent = new Intent(this, InputDataActivity.class);
                    startActivity(inputDataIntent);
                } else {
                    //Toast.makeText(this, "Forkert login", Toast.LENGTH_SHORT).show();
                }
                */
                break;

            case R.id.signUpButton:
                Intent signUpIntent = new Intent(this, SignUpActivity.class);
                startActivity(signUpIntent);
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

    public void firebaseSignIn() {
        progressDialog.show();

        String email = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        Log.d("loginFire", "signInWithEmailAndPassword "+email + password);
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("loginFire", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            if (firebaseUser != null) getDataFromDatabase(firebaseUser);
                            if (Settings.getCurrentUser() != null) {
                                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                                    Settings.setCurrentPupil((Pupil)Settings.getCurrentUser());
                                }
                            }
                            progressDialog.hide();
                        } else {
                            Log.d("loginFire", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
    }

    private void getDataFromDatabase(FirebaseUser firebaseUser) {
        DatabaseReference myRef = db.getReference(firebaseUser.getUid());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String email = dataSnapshot.child("username").getValue(String.class);
                String password = dataSnapshot.child("password").getValue(String.class);
                double height = (double)(dataSnapshot.child("height").getValue(Long.class));
                double weight = (double)(dataSnapshot.child("weight").getValue(Long.class));
                double bmi = (double)(dataSnapshot.child("bmi").getValue(Long.class));
                double calories = (double)(dataSnapshot.child("calories").getValue(Long.class));
                double protein = (double)(dataSnapshot.child("protein").getValue(Long.class));
                double carbs = (double)(dataSnapshot.child("carbs").getValue(Long.class));
                double fat = (double)(dataSnapshot.child("fat").getValue(Long.class));
                Date dateOfBirth = null;
                int age = (dataSnapshot.child("height").getValue(Long.class).intValue());
                int ticket = (dataSnapshot.child("ticket").getValue(Long.class).intValue());
                String gender = dataSnapshot.child("gender").getValue(String.class);

                Settings.setCurrentUser(new Pupil(email,password, true,height,weight,bmi,calories,
                        protein,carbs,fat,null,age,
                        new Experience(1,0),0,gender));

                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                    Settings.setCurrentPupil((Pupil)Settings.getCurrentUser());
                }
                //TODO: denne kode burde ikke være i denne metode, men det virkede kun sådan her

                if (Settings.getCurrentUser().isLoggedIn()) {
                    Intent inputDataIntent = new Intent(LoginActivity.this, InputDataActivity.class);
                    startActivity(inputDataIntent);
                }

                Log.d("dataRead", "Value is: " + email);
                Log.d("dataRead", "Value is: " + password);
                Log.d("dataRead", "Value is: " + height);
                Log.d("dataRead", "Value is: " + weight);
                Log.d("dataRead", "Value is: " + calories);
                Log.d("dataRead", "Value is: " + bmi);
                Log.d("dataRead", "Value is: " + protein);
                Log.d("dataRead", "Value is: " + fat);
                Log.d("dataRead", "Value is: " + carbs);
                Log.d("dataRead", "Value is: " + age);
                Log.d("dataRead", "Value is: " + ticket);
                Log.d("dataRead", "Value is: " + gender);


                //evt kan message bruges
                /*
                Message message = dataSnapshot.getValue(Message.class);
                System.out.println(message);

                 */



            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("dataRead", "Failed to read value.", error.toException());
            }
        });

    }

}
