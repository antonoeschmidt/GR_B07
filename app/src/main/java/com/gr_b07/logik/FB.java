package com.gr_b07.logik;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
import com.gr_b07.InputDataActivity;
import com.gr_b07.LoginActivity;
import com.gr_b07.MainMenuActivity;

import java.util.Date;

public class FB {
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private FirebaseAuth.AuthStateListener authStateListener;

    public FB() {
        this.auth = FirebaseAuth.getInstance();
        this.db = FirebaseDatabase.getInstance();
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    //login
                } else {
                    //logout
                }
            }
        };
        auth.addAuthStateListener(authStateListener);
    }

    public void signUp(final Activity activity, final String email, final String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("createUser", "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();

                            Pupil newUser = new Pupil(true, email, password, false, 0, 0, 0, 0,
                                    0, 0, 0, null, 0,
                                    new Experience(1, 0), 0, "n",0);

                            updateDatabase(newUser, user);
                        } else {
                            Log.d("createUser", "createUserWithEmail:failure", task.getException());

                        }
                    }
                });
    }

    public void updateDatabase(Pupil pupil, FirebaseUser firebaseUser) {
        DatabaseReference myRef = db.getReference(firebaseUser.getUid());
        myRef.setValue(pupil);
    }

    public void getDataFromDatabase(final Activity activity, FirebaseUser firebaseUser) {
        DatabaseReference myRef = db.getReference(firebaseUser.getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String email = dataSnapshot.child("username").getValue(String.class);
                String password = dataSnapshot.child("password").getValue(String.class);
                double height = (double) (dataSnapshot.child("height").getValue(Long.class));
                double weight = (double) (dataSnapshot.child("weight").getValue(Long.class));
                double bmi = (double) (dataSnapshot.child("bmi").getValue(Long.class));
                double calories = (double) (dataSnapshot.child("calories").getValue(Long.class));
                double protein = (double) (dataSnapshot.child("protein").getValue(Long.class));
                double carbs = (double) (dataSnapshot.child("carbs").getValue(Long.class));
                double fat = (double) (dataSnapshot.child("fat").getValue(Long.class));
                Date dateOfBirth = null;
                int age = (dataSnapshot.child("height").getValue(Long.class).intValue());
                int ticket = (dataSnapshot.child("ticket").getValue(Long.class).intValue());
                String gender = dataSnapshot.child("gender").getValue(String.class);
                boolean firstTimeLoggedIn = dataSnapshot.child("firstTimeLoggedIn").getValue(boolean.class);
                int activityLevel = (dataSnapshot.child("activityLevel").getValue(Long.class).intValue());

                Settings.setCurrentUser(new Pupil(firstTimeLoggedIn, email, password, true, height, weight, bmi, calories,
                        protein, carbs, fat, null, age,
                        new Experience(1, 0), ticket, gender,activityLevel));

                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                    Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                }
                //TODO: denne kode burde ikke være i denne metode, men det virkede kun sådan her

                if (Settings.getCurrentUser().isLoggedIn() && Settings.getCurrentUser().isFirstTimeLoggedIn()) {
                    Intent inputDataIntent = new Intent(activity, InputDataActivity.class);
                    activity.startActivity(inputDataIntent);
                } else if (Settings.getCurrentUser().isLoggedIn()) {
                    Intent mainMenuIntent = new Intent(activity, MainMenuActivity.class);
                    activity.startActivity(mainMenuIntent);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("dataRead", "Failed to read value.", error.toException());
            }
        });

    }

    public void firebaseSignIn(final Activity activity, String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("loginFire", "signInWithEmail:success");

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            if (firebaseUser != null) getDataFromDatabase(activity, firebaseUser);
                            if (Settings.getCurrentUser() != null) {
                                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                                    Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                                }
                            }
                        } else {
                            Log.d("loginFire", "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseDatabase getDb() {
        return db;
    }

    public FirebaseAuth.AuthStateListener getAuthStateListener() {
        return authStateListener;
    }


}
