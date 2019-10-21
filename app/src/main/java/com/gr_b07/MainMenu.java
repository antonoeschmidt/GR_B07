package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{
    private Button mainButton1, mainButton2, mainButton3, mainButton4, mainButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mainButton1 = findViewById(R.id.mainmenu_button1); mainButton1.setOnClickListener(this);
        mainButton2 = findViewById(R.id.mainmenu_button2); mainButton1.setOnClickListener(this);
        mainButton3 = findViewById(R.id.mainmenu_button3); mainButton1.setOnClickListener(this);
        mainButton4 = findViewById(R.id.mainmenu_button4); mainButton1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mainButton1){
            Intent nutritionIntent = new Intent(this, NutritionActivity.class); startActivity(nutritionIntent);
        }

        else if (v == mainButton2){
            Intent activityIntent = new Intent(this, ActivityActivity.class); startActivity(activityIntent);
        }

        else if (v == mainButton3){
            Intent socialIntent = new Intent(this, SocialActivity.class); startActivity(socialIntent);
        }
        else if (v == mainButton4){
            Intent rewardsIntent = new Intent(this, RewardsActivity.class); startActivity(rewardsIntent);
        }
        else if (v == mainButton5){
            // Settings not made yet.
        }
    }
}
