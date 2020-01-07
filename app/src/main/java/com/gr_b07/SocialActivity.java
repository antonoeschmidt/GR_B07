package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView usersTextView;
    private Button buttonIncrement;
    boolean firsttime = true;
    // Pupil pupil = Settings.getUsers().getClass().asSubclass(Pupil);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        usersTextView = findViewById(R.id.usersTextView);
        buttonIncrement = findViewById(R.id.buttonIncrement);

        buttonIncrement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonIncrement:
                if (firsttime) {
                    usersTextView.setText(Settings.getUsers().get(1).toString());
                    firsttime = false;
                }
                // else
                break;
        }
    }
}
