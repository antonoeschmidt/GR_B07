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

import com.gr_b07.logik.FB;
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


        rProgress.setMax(Settings.getCurrentPupil().getExperience().getLevel()*5+10);
        rProgress.setProgress(0);


        levelTextView.setText("Level: " + Integer.toString(Settings.getCurrentPupil().getExperience().getLevel()));


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
                levelTextView.setText("Level: " + Settings.getCurrentPupil().getExperience().getLevel());
                Settings.getCurrentPupil().getExperience().setXp(Settings.getCurrentPupil().getExperience().getXp()+2);
                rProgress.setProgress(Settings.getCurrentPupil().getExperience().getXp());
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
            Settings.getCurrentPupil().getExperience().setLevel(Settings.getCurrentPupil().getExperience().getLevel()+1);
            Settings.getCurrentPupil().getExperience().setXp(Settings.getCurrentPupil().getExperience().getXp()%rProgress.getMax());

            rProgress.setMax(Settings.getCurrentPupil().getExperience().getLevel()*5+10);
            ;
            rProgress.setProgress(Settings.getCurrentPupil().getExperience().getXp());

            Settings.getCurrentPupil().setTicket(Settings.getCurrentPupil().getTicket()+1);

        }
    }
    public void updateTextViews() {
        levelTextView.setText("Level: " + Settings.getCurrentPupil().getExperience().getLevel());
        xpTextView.setText("Progress: \n " + Settings.getCurrentPupil().getExperience().getXp() + " / " + Settings.getCurrentPupil().getExperience().getLevel()*5+10);
        rProgress.setProgress(Settings.getCurrentPupil().getExperience().getXp());
        if (Settings.getCurrentPupil().getTicket()>= 1){
            turnOnTextBlink();
        }
        else rewardButton.clearAnimation();
    }
    public void turnOnTextBlink(){
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        rewardButton.startAnimation(anim);
    }
}
