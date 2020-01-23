package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gr_b07.games.GameActivity;
import com.gr_b07.logik.Settings;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class RewardsActivity extends AppCompatActivity implements View.OnClickListener {
    // private ProgressBar rProgress;
    private TextView levelTextView, totalXPtext, totalNutritionXPtextView, totalActivityXPtextView, totalSocialXPtextView, ticketTextView;

    private Button rewardButton, rewardTestButtonNutrition, rewardTestButtonActivity, rewardTestButtonSocial;
    private CircularProgressBar circularProgressBarNutrition, circularProgressBarActivity, circularProgressBarSocial;
    private int xpType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        circularProgressBarNutrition = findViewById(R.id.circularProgressBarNutrition);
        circularProgressBarActivity = findViewById(R.id.circularProgressBarActivity);
        circularProgressBarSocial = findViewById(R.id.circularProgressBarSocial);

        levelTextView = findViewById(R.id.textViewLevel);
        totalNutritionXPtextView = findViewById(R.id.totalNutritionXPText);
        totalActivityXPtextView = findViewById(R.id.totalActivityXPText);
        totalSocialXPtextView = findViewById(R.id.totalSocialXPText);
        totalXPtext = findViewById(R.id.totalXPText);
        ticketTextView = findViewById(R.id.ticketTextView);

        rewardTestButtonNutrition = findViewById(R.id.buttonRewardTestNutrition);
        rewardTestButtonActivity = findViewById(R.id.buttonRewardTestActivity);
        rewardTestButtonSocial = findViewById(R.id.buttonRewardTestSocial);
        rewardButton = findViewById(R.id.buttonRewards);
        rewardTestButtonNutrition.setOnClickListener(this);
        rewardTestButtonActivity.setOnClickListener(this);
        rewardTestButtonSocial.setOnClickListener(this);
        rewardButton.setOnClickListener(this);
        updateTextViews();
        updateCircleBars();

        levelTextView.setText("Niveau: " + Integer.toString(Settings.getCurrentPupil().getExperience().getLevel()));
        ticketTextView.setText("Lodder: " + Settings.getCurrentPupil().getExperience().getTicket());

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTextViews();
    }

    @Override
    public void onClick(View v) {
        if (v == rewardTestButtonNutrition) {
            Settings.getCurrentPupil().getExperience().setNutritionXP(Settings.getCurrentPupil().getExperience().getNutritionXP() + 2);
            xpType = 0;
        }
        if (v == rewardTestButtonActivity) {
            Settings.getCurrentPupil().getExperience().setActivityXP(Settings.getCurrentPupil().getExperience().getActivityXP() + 2);
            xpType = 1;
        }
        if (v == rewardTestButtonSocial) {
            Settings.getCurrentPupil().getExperience().setSocialXP(Settings.getCurrentPupil().getExperience().getSocialXP() + 2);
            xpType = 2;
        }
        if (v == rewardButton) {
            Intent gameIntent = new Intent(this, GameActivity.class);
            startActivity(gameIntent);
        }
        updateTextViews();
        updateCircleBars();
    }

    public void updateTextViews() {
        levelTextView.setText("Level " + Settings.getCurrentPupil().getExperience().getLevel());
        totalXPtext.setText("Total XP\n " + Settings.getCurrentPupil().getExperience().getTotalXP() + " / " + (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
        totalNutritionXPtextView.setText("Nutrition Points: " + Settings.getCurrentPupil().getExperience().getNutritionXP());
        totalActivityXPtextView.setText("Activity Points: " + Settings.getCurrentPupil().getExperience().getActivityXP());
        totalSocialXPtextView.setText("Social Points: " + Settings.getCurrentPupil().getExperience().getSocialXP());
        ticketTextView.setText("Tickets: " + Settings.getCurrentPupil().getExperience().getTicket());
    }

    public void updateCircleBars() {
        circularProgressBarNutrition.setProgressMax((Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
        circularProgressBarActivity.setProgressMax((Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
        circularProgressBarSocial.setProgressMax((Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
        circularProgressBarNutrition.setProgress(Settings.getCurrentPupil().getExperience().getNutritionXP());
        circularProgressBarActivity.setProgress(Settings.getCurrentPupil().getExperience().getActivityXP());
        circularProgressBarSocial.setProgress(Settings.getCurrentPupil().getExperience().getSocialXP());
        if (Settings.getCurrentPupil().getExperience().getNutritionXP() == 0) {
            circularProgressBarActivity.setStartAngle(0);
        } else {
            circularProgressBarActivity.setStartAngle(360 / (circularProgressBarNutrition.getProgressMax()
                    / Settings.getCurrentPupil().getExperience().getNutritionXP()));
        }
        if (Settings.getCurrentPupil().getExperience().getActivityXP() + Settings.getCurrentPupil().getExperience().getNutritionXP() == 0) {
            circularProgressBarSocial.setStartAngle(0);
        } else {
            circularProgressBarSocial.setStartAngle(360 / (circularProgressBarNutrition.getProgressMax()
                    / (Settings.getCurrentPupil().getExperience().getActivityXP() + Settings.getCurrentPupil().getExperience().getNutritionXP())));
        }

    }

}
