package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ProgressBar;

public class NutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        ProgressBar pProgress = findViewById(R.id.progressbarProtein);
        ProgressBar cProgress = findViewById(R.id.progressbarCarbs);
        ProgressBar fProgress = findViewById(R.id.progressbarFat);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.color.colorProtein);

        pProgress.setProgress(25);   // Main Progress

        /*
        mProgress.setSecondaryProgress(50); // Secondary Progress
        mProgress.setMax(100); // Maximum Progress
        mProgress.setProgressDrawable(drawable);
         */
    }
}
