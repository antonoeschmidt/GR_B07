package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView usersTextView;
    private Button buttonIncrement;
    private AutoCompleteTextView classmatesTextView;
    boolean firsttime = true;
    private ArrayList<Pupil> pupils = new ArrayList<Pupil>();
    FB fb = new FB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        usersTextView = findViewById(R.id.usersTextView);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        buttonIncrement.setOnClickListener(this);

        classmatesTextView = findViewById(R.id.classmatesTextView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonIncrement:
                /*
                fb.getAllUsersFromDatabase();
                for (User user : Settings.getUsers()){
                    usersTextView.setText(usersTextView.getText().toString() + user.getUsername());
                }
                Log.d(Settings.getUsers().toString(), "onClick: ");
                */


                //for (User user : Settings.getUsers())
                  //  if (user.getClass().equals(Pupil.class)) { // SHOULD BE if (user.getClass().equals(Pupil.class && er i samme klasse/skole etc.))
                        //Pupil pupil = (Pupil) user;
                        //pupils.add(pupil);
                        //ArrayAdapter<String> adapter = new ArrayAdapter<>
                                //(this, android.R.layout.select_dialog_item, SocialActivity.);
                        //usersTextView.setText(usersTextView.getText() + "\n" + pupil.getUsername());

                break;
        }
    }
}
