package com.gr_b07.logik;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.gr_b07.MainMenuActivity;

import java.util.ArrayList;
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

    public void signUp(final ProgressDialog progressDialog, final Activity activity, final String email, final String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("createUser", "createUserWithEmail:success");
                            Toast.makeText(activity, "Sign Up Success", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = auth.getCurrentUser();

                            Pupil newUser = new Pupil(true, email, password, false, 0, 0, 0, 0,
                                    0, 0, 0, 0, 0,
                                    new Experience(1, 0), 0, "n", 0,new ArrayList<Reward>());

                            updateDatabase(newUser, user);
                            progressDialog.hide();
                        } else {
                            Log.d("createUser", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Sign Up Failed", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }

    public void updateDatabase(Pupil pupil, FirebaseUser firebaseUser) {
        DatabaseReference myRef = db.getReference(firebaseUser.getUid());
        myRef.setValue(pupil);
    }

    public void checkFirstTimeLoggedInFromDatabase(final Activity activity, String UID) {
        DatabaseReference myRef = db.getReference(UID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot d) {

                Settings.setCurrentUser(getUserFromDatabase(d));

                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                    Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                }
                //TODO: denne kode burde ikke være i denne metode, men det virkede kun sådan her
                //pga async netværkskommunikation - burde være i SignIn/Login

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

    public void firebaseSignIn(final ProgressDialog progressDialog, final Activity activity, String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("loginFire", "signInWithEmail:success");
                            Toast.makeText(activity, "Logged In Success", Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            if (firebaseUser != null)
                                checkFirstTimeLoggedInFromDatabase(activity, firebaseUser.getUid());

                            if (Settings.getCurrentUser() != null) {
                                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                                    Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                                }
                            }

                            progressDialog.hide();

                        } else {
                            Log.d("loginFire", "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Logged In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void getAllUsersFromDatabase() {
        Settings.getUsers().clear();
        DatabaseReference myRef = db.getReference();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        Settings.addUser(getUserFromDatabase(d));
                        Log.d(getUserFromDatabase(d).toString(), "getUsersFromDatabase : Hvilke users?");
                    }
                }
            }//onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public User getUserFromDatabase(DataSnapshot d){

        String email = d.child("username").getValue(String.class);
        String password = d.child("password").getValue(String.class);
        double height = (double) (d.child("height").getValue(Long.class));
        double weight = (double) (d.child("weight").getValue(Long.class));
        double bmi = (double) (d.child("bmi").getValue(Long.class));
        double calories = (double) (d.child("calories").getValue(Long.class));
        double protein = (double) (d.child("protein").getValue(Long.class));
        double carbs = (double) (d.child("carbs").getValue(Long.class));
        double fat = (double) (d.child("fat").getValue(Long.class));
        //Date dateOfBirth = (Date) (d.child("dateOfBirth").getValue(Date.class));
        long dateOfBirth = (d.child("dateOfBirth").getValue(Long.class));
        int age = (d.child("height").getValue(Long.class).intValue());
        //Experience experience = (Experience) (d.child("experience").getValue(Experience.class));
        //Log.d(experience.toString(), "getUserFromDatabase: ");
        int ticket = (d.child("ticket").getValue(Long.class).intValue());
        String gender = d.child("gender").getValue(String.class);
        boolean firstTimeLoggedIn = d.child("firstTimeLoggedIn").getValue(boolean.class);
        int activityLevel = (d.child("activityLevel").getValue(Long.class).intValue());

        return new Pupil(firstTimeLoggedIn, email, password, true, height, weight, bmi, calories,
                protein, carbs, fat, dateOfBirth, age,
                new Experience(1,0), ticket, gender, activityLevel,new ArrayList<Reward>());

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
