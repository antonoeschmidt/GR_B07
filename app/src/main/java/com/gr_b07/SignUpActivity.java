package com.gr_b07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gr_b07.logik.Experience;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.User;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private ImageView imageViewUser;
    private ImageView imageViewPass;
    private Button signUpButton;
    private ProgressDialog progressDialog;

    private FirebaseAuth auth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        imageViewUser = findViewById(R.id.imageViewLogin);
        imageViewPass = findViewById(R.id.imageViewPassword);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Opretter bruger");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpButton:
                signUp();
                break;
        }
    }

    private void signUp() {
        progressDialog.show();

        final String email = usernameText.getText().toString();
        final String password = passwordText.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("createUser", "createUserWithEmail:success");
                            Toast.makeText(SignUpActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = auth.getCurrentUser();
                            insertToDatabase(email, password, user);
                            progressDialog.hide();
                        } else {
                            Log.d("createUser", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });


    }

    private void insertToDatabase(String email, String password, FirebaseUser firebaseUser) {

        DatabaseReference myRef = db.getReference(firebaseUser.getUid());
        Pupil newUser = new Pupil(true, email,password,false,0,0,0,0,
                0,0,0,null,0,
                new Experience(1,0),0,"male");
        myRef.setValue(newUser);

        /*
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Pupil value = dataSnapshot.getValue(Pupil.class);
                Log.d("dataRead", "Value is: " + value.getUsername());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("dataRead", "Failed to read value.", error.toException());
            }
        });
         */


    }
}
