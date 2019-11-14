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
import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar pProgress, cProgress, fProgress, cRing;
    private TextView caloriesTextView, proteinTextView, carbsTextView, fatTextView;
    private Button testButton, breakfastButton, lunchButton, dinnerButton, snacksButton;
    private InputStream inputStream;
    private String[] data;
    protected static ArrayList<Food> foodDB = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        cRing = findViewById(R.id.progressbarCalories);
        pProgress = findViewById(R.id.progressbarProtein);
        cProgress = findViewById(R.id.progressbarCarbs);
        fProgress = findViewById(R.id.progressbarFat);

        caloriesTextView = findViewById(R.id.caloriesTextView);
        proteinTextView = findViewById(R.id.proteinTextView);
        carbsTextView = findViewById(R.id.carbsTextView);
        fatTextView = findViewById(R.id.fatTextView);

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

        // TODO: IIFYM-formel ud fra alder, højde, vægt, aktivitetsniveau.
        pProgress.setMax(149);
        cProgress.setMax(372);
        fProgress.setMax(99);
        cRing.setMax(3200);

        updateView();
        createDBarray();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.testButton:
                eatFood("test");
                Log.d("Output ", "" + accessDatabase("fisk").getFat());
                updateView();
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
    //TODO: kan slettes evt
    public void eatFood(String food){
        Food foodToEat = accessDatabase(food);
        Settings.getCurrentUser().setCalories(Settings.getCurrentUser().getCalories() + foodToEat.getCalories());
        Settings.getCurrentUser().setProtein(Settings.getCurrentUser().getProtein() + foodToEat.getProtein());
        Settings.getCurrentUser().setCarbs(Settings.getCurrentUser().getCarbs() + foodToEat.getCarbs());
        Settings.getCurrentUser().setFat(Settings.getCurrentUser().getFat() + foodToEat.getFat());
        cRing.setProgress((int) Math.round(Settings.getCurrentUser().getCalories()));
        pProgress.setProgress((int) Math.round(Settings.getCurrentUser().getProtein()));
        cProgress.setProgress((int) Math.round(Settings.getCurrentUser().getCarbs()));
        fProgress.setProgress((int) Math.round(Settings.getCurrentUser().getFat()));
    }

    public void updateView(){
        pProgress.setProgress((int) Math.round(Settings.getCurrentUser().getProtein()));
        cProgress.setProgress((int) Math.round(Settings.getCurrentUser().getCarbs()));
        fProgress.setProgress((int) Math.round(Settings.getCurrentUser().getFat()));
        cRing.setProgress((int) Math.round(Settings.getCurrentUser().getCalories()));
        caloriesTextView.setText("Calories: " + cRing.getProgress() + "  /  " + cRing.getMax());
        proteinTextView.setText("Protein: " + pProgress.getProgress() +  "  /  " + pProgress.getMax());
        carbsTextView.setText("Carbs: "+ cProgress.getProgress() + "  /  " + cProgress.getMax());
        fatTextView.setText("Fat: " + fProgress.getProgress() + "  /  " + fProgress.getMax());
    }
    //TODO: kan slettes evt
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

    private void createDBarray() {
        inputStream = getResources().openRawResource(R.raw.mad);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                data = csvLine.split(";");
                foodDB.add(new Food(data[0], Double.parseDouble(data[1]),Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]),Double.parseDouble(data[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Error ", "Cannot read file");
        }
        for (Food food :
                foodDB) {
            System.out.println(food.getName());
        }



    }


}
