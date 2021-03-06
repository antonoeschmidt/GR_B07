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
import com.gr_b07.LoginActivity;
import com.gr_b07.MainMenuActivity;

import java.util.ArrayList;

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
                            Toast.makeText(activity, "Bruger oprettet", Toast.LENGTH_SHORT).show();

                            Pupil newUser = new Pupil(true, email, password, auth.getUid(), new Physique(0, 0, 0),
                                    new PersonalInfo("", "", "n", 0, 0),
                                    new Experience(1, 0, 0, 0, 0, false, false, false, false),
                                    new ArrayList<Meal>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<Reward>()
                            );

                            Settings.setCurrentPupil(newUser);
                            updateDatabase();
                            progressDialog.hide();
                            activity.finish();
                        } else {
                            Log.d("createUser", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Bruger kunne ikke oprettes", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();

                        }
                    }
                });
    }

    public void updateDatabase() {
        DatabaseReference myRef = db.getReference(auth.getCurrentUser().getUid());
        myRef.setValue(Settings.getCurrentPupil());
    }

    public void checkFirstTimeLoggedInFromDatabase(final Activity activity, String UID) {
        DatabaseReference myRef = db.getReference(UID);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot d) {
                Settings.setCurrentUser(d.getValue(Pupil.class));

                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                    Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                }
                //nedenstående kode burde ikke være i denne metode, men det virkede kun sådan her
                //pga async netværkskommunikation - burde være i SignIn/Login
                if (auth != null && Settings.getCurrentUser().isFirstTimeLoggedIn()) {
                    Intent inputDataIntent = new Intent(activity, InputDataActivity.class);
                    activity.startActivity(inputDataIntent);
                } else if (Settings.logginIn) {
                    Intent mainMenuIntent = new Intent(activity, MainMenuActivity.class);
                    activity.startActivity(mainMenuIntent);
                    Settings.logginIn = false;
                    activity.finish();
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
                            //Toast.makeText(activity, "Logged In Success", Toast.LENGTH_SHORT).show();

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            if (firebaseUser != null) {
                                Settings.logginIn = true;
                                checkFirstTimeLoggedInFromDatabase(activity, firebaseUser.getUid());

                            }


                            if (Settings.getCurrentUser() != null) {
                                if (Settings.getCurrentUser().getClass().equals(Pupil.class)) {
                                    Settings.setCurrentPupil((Pupil) Settings.getCurrentUser());
                                }
                            }

                            progressDialog.hide();

                        } else {
                            Log.d("loginFire", "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Login fejlede", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
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
                        Settings.addUser(d.getValue(Pupil.class));
                        Log.d(d.getValue(Pupil.class).toString(), "getUsersFromDatabase : Hvilke users?");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
