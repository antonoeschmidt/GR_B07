package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gr_b07.logik.Experience;
import com.gr_b07.logik.Level;
import com.gr_b07.logik.Settings;

public class RewardsActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar rProgress;
    private TextView levelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        // TODO: Remove this mockup;
        Settings.getCurrentUser().getExperience().setCurrentLevel(Settings.getCurrentUser().getExperience().getLevels()[0]);

        rProgress = findViewById(R.id.progressbarExperience);
        levelTextView = findViewById(R.id.textViewLevel);
        levelTextView.setText(Settings.getCurrentUser().getExperience().getCurrentLevel().toString());


    }

    @Override
    public void onClick(View v) {

    }
}
