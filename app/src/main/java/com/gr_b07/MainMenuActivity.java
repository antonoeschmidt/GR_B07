package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gr_b07.logik.Settings;
import com.gr_b07.nutrition.NutritionActivity;
import com.gr_b07.social.SocialActivity;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mainButton1, mainButton2, mainButton3, mainButton4, mainButton5;
    private TextView levelTextView, xpTextView;
    private ProgressBar xpProgressBar;
    private ImageView ticketView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainButton1 = findViewById(R.id.mainmenu_button1);
        mainButton1.setOnClickListener(this);
        mainButton2 = findViewById(R.id.mainmenu_button2);
        mainButton2.setOnClickListener(this);
        mainButton3 = findViewById(R.id.mainmenu_button3);
        mainButton3.setOnClickListener(this);
        mainButton4 = findViewById(R.id.mainmenu_button4);
        mainButton4.setOnClickListener(this);
        mainButton5 = findViewById(R.id.mainmenu_button5);
        mainButton5.setOnClickListener(this);

        levelTextView = findViewById(R.id.mainmenu_levelTextView);
        xpTextView = findViewById(R.id.mainmenu_xpTextView);
        xpProgressBar = findViewById(R.id.progressBarXP);
        ticketView = findViewById(R.id.ticketImageView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMainMenu();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainmenu_button1:
            Intent nutritionIntent = new Intent(this, NutritionActivity.class); startActivity(nutritionIntent); break;
            case R.id.mainmenu_button2:
            Intent activityIntent = new Intent(this, ActivityActivity.class); startActivity(activityIntent); break;
            case R.id.mainmenu_button3:
            Intent socialIntent = new Intent(this, SocialActivity.class); startActivity(socialIntent); break;
            case R.id.mainmenu_button4:
                Intent rewardsIntent = new Intent(this, RewardsActivity.class); startActivity(rewardsIntent); break;
            case R.id.mainmenu_button5:
                Intent inputDataIntent = new Intent(this, InputDataActivity.class); startActivity(inputDataIntent);
                break;
        }

    }
    public void updateMainMenu() {
        levelTextView.setText(Integer.toString(Settings.getCurrentPupil().getExperience().getLevel()).toString());
        xpTextView.setText((Integer.toString(Settings.getCurrentPupil().getExperience().getTotalXP())) + " / " + Integer.toString(Settings.getCurrentPupil().getExperience().getLevel()*5+10));
        xpProgressBar.setMax(Settings.getCurrentPupil().getExperience().getLevel()*5+10);
        xpProgressBar.setProgress(Settings.getCurrentPupil().getExperience().getTotalXP());
        if (Settings.getCurrentPupil().getExperience().getTicket() >= 1){
            ticketView.setVisibility(View.VISIBLE);
        }
        else if (Settings.getCurrentPupil().getExperience().getTicket()==0){
            ticketView.setVisibility(View.INVISIBLE);
        }
    }
}
