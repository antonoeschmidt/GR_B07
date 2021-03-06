package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.logik.Settings;
import com.gr_b07.nutrition.NutritionActivity;
import com.gr_b07.social.SocialActivity;

import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mainButton1, mainButton2, mainButton3, mainButton4, mainButton5;
    private TextView levelTextView, xpTextView;
    private ProgressBar xpProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Sentry.init("https://ae47774e314c4b79a5b3eff0ec2dbecb@sentry.io/1895656", new AndroidSentryClientFactory(this));
            Sentry.capture(this.getClass().getSimpleName());
        }

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
        // NullPointerException: Attempt to invoke virtual method 'com.gr_b07.logik.Experience com.gr_b07.logik.Pupil.getExperience()' on a null object reference
        levelTextView.setText(Integer.toString(Settings.getCurrentPupil().getExperience().getLevel()).toString());
        xpTextView.setText((Integer.toString(Settings.getCurrentPupil().getExperience().getTotalXP())) + " / " + Integer.toString(Settings.getCurrentPupil().getExperience().getLevel()*5+10));
        xpProgressBar.setMax(Settings.getCurrentPupil().getExperience().getLevel()*5+10);
        xpProgressBar.setProgress(Settings.getCurrentPupil().getExperience().getTotalXP());

    }
}
