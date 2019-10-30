package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NutritionActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar pProgress, cProgress, fProgress;
    private double consumedCalories, totalCalories;
    private TextView caloriesTextView;
    private Button testButton, breakfastButton, lunchButton, dinnerButton, snacksButton;
    private InputStream inputStream;
    private String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        pProgress = findViewById(R.id.progressbarProtein);
        cProgress = findViewById(R.id.progressbarCarbs);
        fProgress = findViewById(R.id.progressbarFat);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        testButton = findViewById(R.id.testButton);
        breakfastButton = findViewById(R.id.breakfastButton);
        lunchButton = findViewById(R.id.lunchButton);
        dinnerButton = findViewById(R.id.dinnerButton);
        snacksButton = findViewById(R.id.snacksButton);
        testButton.setOnClickListener(this);
        breakfastButton.setOnClickListener(this);
        lunchButton.setOnClickListener(this);
        dinnerButton.setOnClickListener(this);
        snacksButton.setOnClickListener(this);
        // TODO: Lave en eller anden form for IIFYM der ud fra aktivitetsniveau, alder, højde og vægt beregner hvor meget protein, kulhydrat og fedt man skal have.


        // eksempel med mine daglige macros
        pProgress.setMax(149);
        cProgress.setMax(372);
        fProgress.setMax(99);

        // Logger lige min morgenmad fra 30/10 som eksempel (lol) - Harald
        pProgress.setProgress((int) Math.round(Settings.getCurrentUser().getProtein()));   // Main Progress
        cProgress.setProgress((int) Math.round(Settings.getCurrentUser().getCarbs()));
        fProgress.setProgress((int) Math.round(Settings.getCurrentUser().getFat()));

        // Protein og fedt er 4 kcal pr. gram og fedt er 9.
        //consumedCalories = pProgress.getProgress()*4+cProgress.getProgress()*4+fProgress.getProgress()*9;
        consumedCalories = (int) Math.round(Settings.getCurrentUser().getCalories());
        totalCalories = pProgress.getMax() * 4 + cProgress.getMax() * 4 + fProgress.getMax() * 9;

        caloriesTextView.setText(consumedCalories + "  /  " + totalCalories );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testButton:
                eatFood("banan");
                caloriesTextView.setText(consumedCalories + "  /  " + totalCalories);
                Toast.makeText(this, "Hej", Toast.LENGTH_SHORT).show();
                Log.d("Output ", "" + accessDatabase("fisk").getFat());
                break;
            case R.id.breakfastButton:
                Intent breakfastIntent = new Intent(this, BreakfastActivity.class); startActivity(breakfastIntent);
                break;
            case R.id.lunchButton:
                Intent lunchIntent = new Intent(this, LunchActivity.class); startActivity(lunchIntent);
                break;
            case R.id.dinnerButton:
                Intent dinnerIntent = new Intent(this, DinnerActivity.class); startActivity(dinnerIntent);
                break;
            case R.id.snacksButton:
                Intent snacksIntent = new Intent(this, SnacksActivity.class); startActivity(snacksIntent);
                break;

        }

    }

    public void eatFood(String food){
        Food foodToEat = accessDatabase(food);
        Settings.getCurrentUser().setCalories(Settings.getCurrentUser().getCalories()+foodToEat.getCalories());
        Settings.getCurrentUser().setProtein(Settings.getCurrentUser().getProtein()+foodToEat.getProtein());
        Settings.getCurrentUser().setCarbs(Settings.getCurrentUser().getCarbs()+foodToEat.getCarbs());
        Settings.getCurrentUser().setFat(Settings.getCurrentUser().getFat()+foodToEat.getFat());
        consumedCalories = consumedCalories+foodToEat.getCalories();
        pProgress.setProgress((int) Math.round(Settings.getCurrentUser().getProtein()));
        cProgress.setProgress((int) Math.round(Settings.getCurrentUser().getCarbs()));
        fProgress.setProgress((int) Math.round(Settings.getCurrentUser().getFat()));
    }

    private Food accessDatabase(String food) {
        inputStream = getResources().openRawResource(R.raw.mad);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                data = csvLine.split(";");
                if (data[0].equalsIgnoreCase(food)) {
                    return new Food(data[0], Double.parseDouble(data[1]),Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]),Double.parseDouble(data[4]));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Error ", "Cannot read file");
        }
        return null;
    }


}
