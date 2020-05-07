package com.gr_b07;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.Experience;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.PersonalInfo;
import com.gr_b07.logik.Physique;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Reward;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.SpringClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText passwordText;
    private Button signUpButton;
    private ProgressDialog progressDialog;
    private SpringClient springClient = new SpringClient(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Opretter bruger");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpButton:

                if (emailIsValid(usernameText.getText().toString()) && passwordIsValid(passwordText.getText().toString())) {
                    progressDialog.show();
                    final String email = usernameText.getText().toString();
                    final String password = passwordText.getText().toString();
                    Pupil newUser = new Pupil(true, email, password,"thisshouldhopefullybeligemeget", new Physique(0, 0, 0),
                            new PersonalInfo("", "", "n", 0, 0),
                            new Experience(1, 0, 0, 0, 0, false, false, false, false),
                            new ArrayList<Meal>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<Reward>()
                    );
                    Settings.setCurrentPupil(newUser);
                    springClient.createUser(newUser);
                    Intent inputDataIntent = new Intent(this, InputDataActivity.class);
                    startActivity(inputDataIntent);
                    //fb.signUp(progressDialog,this, email,password);
                } else if (!emailIsValid(usernameText.getText().toString())) {
                    Toast.makeText(this, "E-mail har ikke det rigtige format", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Adgangskode skal være på mindst 6 tegn", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean emailIsValid (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    public boolean passwordIsValid (String password) {
        return password.length() >= 6;
    }


}
