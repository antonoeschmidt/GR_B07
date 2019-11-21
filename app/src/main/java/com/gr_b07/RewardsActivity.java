package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gr_b07.logik.Settings;

public class RewardsActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar rProgress;
    private TextView levelTextView, xpTextView;
    private Button rewardTestButton, rewardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        rProgress = findViewById(R.id.progressbarExperience);
        levelTextView = findViewById(R.id.textViewLevel);
        xpTextView = findViewById(R.id.textViewXpProgress);
        rewardTestButton = findViewById(R.id.buttonRewardTest);
        rewardButton = findViewById(R.id.buttonRewards);
        rewardTestButton.setOnClickListener(this);
        rewardButton.setOnClickListener(this);


        rProgress.setMax(Settings.getCurrentUser().getExperience().getRange());
        rProgress.setProgress(0);


        levelTextView.setText("Level: " + Integer.toString(Settings.getCurrentUser().getExperience().getLevel()));


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTextViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRewardTest:
                levelTextView.setText("Level: " + Settings.getCurrentUser().getExperience().getLevel());
                Settings.getCurrentUser().getExperience().setXp(Settings.getCurrentUser().getExperience().getXp()+2);
                rProgress.setProgress(Settings.getCurrentUser().getExperience().getXp());
                checkLevel();
                updateTextViews();
                break;
            case R.id.buttonRewards:
                updateTextViews();
                Intent gameIntent = new Intent(this, GameActivity.class);
                startActivity(gameIntent);
                break;
        }

    }
    public void checkLevel(){
        if (rProgress.getProgress() >= rProgress.getMax()){
            Settings.getCurrentUser().getExperience().setLevel(Settings.getCurrentUser().getExperience().getLevel()+1);
            Settings.getCurrentUser().getExperience().fixRange();
            Settings.getCurrentUser().getExperience().setXp(Settings.getCurrentUser().getExperience().getXp()%rProgress.getMax());

            rProgress.setMax(Settings.getCurrentUser().getExperience().getRange());
            rProgress.setProgress(Settings.getCurrentUser().getExperience().getXp());

            Settings.getCurrentUser().setTicket(Settings.getCurrentUser().getTicket()+1);

        }
    }
    public void updateTextViews() {
        levelTextView.setText("Level: " + Settings.getCurrentUser().getExperience().getLevel());
        xpTextView.setText("Progress: \n " + Settings.getCurrentUser().getExperience().getXp() + " / " + Settings.getCurrentUser().getExperience().getRange());
        if (Settings.getCurrentUser().getTicket()>= 1){
            turnOnTextBlink();
        }
        else rewardButton.clearAnimation();
    }
    public void turnOnTextBlink(){
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(400);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        rewardButton.startAnimation(anim);
    }
}
