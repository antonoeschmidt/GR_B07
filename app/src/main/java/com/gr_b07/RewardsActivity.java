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
    private CircularProgressBar circularProgressBar;
    private int xpType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        circularProgressBar = findViewById(R.id.circularProgressBar);
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

        circularProgressBar.setProgressMax(Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10);
        circularProgressBar.setProgress(0);

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
        circularProgressBar.setProgressMax((Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
        circularProgressBar.setProgress(Settings.getCurrentPupil().getExperience().getTotalXP());
        levelTextView.setText("Niveau " + Settings.getCurrentPupil().getExperience().getLevel());
    }

    public void checkLevel() {
        circularProgressBar.setProgress(Settings.getCurrentPupil().getExperience().getTotalXP());
        if (circularProgressBar.getProgress() >= circularProgressBar.getProgressMax()) {
            if (xpType == 0) {
                Settings.getCurrentPupil().getExperience().setNutritionXP(Settings.getCurrentPupil().getExperience().getTotalXP() % (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
                Settings.getCurrentPupil().getExperience().setActivityXP(0);
                Settings.getCurrentPupil().getExperience().setSocialXP(0);
            }
            if (xpType == 1) {
                Settings.getCurrentPupil().getExperience().setActivityXP(Settings.getCurrentPupil().getExperience().getTotalXP() % (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
                Settings.getCurrentPupil().getExperience().setNutritionXP(0);
                Settings.getCurrentPupil().getExperience().setSocialXP(0);
            }
            if (xpType == 2) {
                Settings.getCurrentPupil().getExperience().setSocialXP(Settings.getCurrentPupil().getExperience().getTotalXP() % (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
                Settings.getCurrentPupil().getExperience().setNutritionXP(0);
                Settings.getCurrentPupil().getExperience().setActivityXP(0);
            }
            Settings.getCurrentPupil().getExperience().setLevel(Settings.getCurrentPupil().getExperience().getLevel() + 1);
            Settings.getCurrentPupil().getExperience().setTicket(Settings.getCurrentPupil().getExperience().getTicket() + 1);

        }
    }

    public void updateTextViews() {
        levelTextView.setText("Niveau " + Settings.getCurrentPupil().getExperience().getLevel());
        totalXPtext.setText("Total XP\n " + Settings.getCurrentPupil().getExperience().getTotalXP()+ " / " + (Settings.getCurrentPupil().getExperience().getLevel() * 5 + 10));
        totalNutritionXPtextView.setText("Ernæringspoint: " + Settings.getCurrentPupil().getExperience().getNutritionXP());
        totalActivityXPtextView.setText("Aktivitetspoint: " + Settings.getCurrentPupil().getExperience().getActivityXP());
        totalSocialXPtextView.setText("Venskabspoint: " + Settings.getCurrentPupil().getExperience().getSocialXP());
        circularProgressBar.setProgress(Settings.getCurrentPupil().getExperience().getTotalXP());
        ticketTextView.setText("Lodder: " + Settings.getCurrentPupil().getExperience().getTicket());
    }
}
