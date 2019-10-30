package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NutritionActivity extends AppCompatActivity {
    private ProgressBar pProgress, cProgress, fProgress;
    private int consumedCalories, totalCalories;
    private TextView caloriesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        pProgress = findViewById(R.id.progressbarProtein);
        cProgress = findViewById(R.id.progressbarCarbs);
        fProgress = findViewById(R.id.progressbarFat);
        caloriesTextView = findViewById(R.id.caloriesTextView);

        // TODO: Lave en eller anden form for IIFYM der ud fra aktivitetsniveau, alder, højde og vægt beregner hvor meget protein, kulhydrat og fedt man skal have.


        // eksempel med mine daglige macros
        pProgress.setMax(149);
        cProgress.setMax(372);
        fProgress.setMax(99);

        // Logger lige min morgenmad fra 30/10 som eksempel (lol) - Harald
        pProgress.setProgress(89);   // Main Progress
        cProgress.setProgress(56);
        fProgress.setProgress(59);

        // Protein og fedt er 4 kcal pr. gram og fedt er 9.
        consumedCalories = pProgress.getProgress()*4+cProgress.getProgress()*4+fProgress.getProgress()*9;
        totalCalories = pProgress.getMax() * 4 + cProgress.getMax() * 4 + fProgress.getMax() * 9;
        Log.d(Integer.toString(consumedCalories), "onCreate: ");

        caloriesTextView.setText(consumedCalories + "  /  " + totalCalories );

    }
}
