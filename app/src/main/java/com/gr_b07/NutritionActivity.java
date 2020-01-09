package com.gr_b07;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.Food;
import com.gr_b07.logik.Settings;
import com.gr_b07.nutrition.BreakfastActivity;
import com.gr_b07.nutrition.DinnerActivity;
import com.gr_b07.nutrition.LunchActivity;
import com.gr_b07.nutrition.SnacksActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar pProgress, cProgress, fProgress, cRing;
    private TextView caloriesTextView, proteinTextView, carbsTextView, fatTextView;
    private Button breakfastButton, lunchButton, dinnerButton, snacksButton;
    private InputStream inputStream;
    private String[] data;
    protected static ArrayList<Food> foodDB = new ArrayList<>();
    public static String[] foodAutoText;

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

        breakfastButton = findViewById(R.id.breakfastButton);
        lunchButton = findViewById(R.id.lunchButton);
        dinnerButton = findViewById(R.id.dinnerButton);
        snacksButton = findViewById(R.id.snacksButton);

        breakfastButton.setOnClickListener(this);
        lunchButton.setOnClickListener(this);
        dinnerButton.setOnClickListener(this);
        snacksButton.setOnClickListener(this);

        // TODO: IIFYM-formel mangler at ændre sig ud fra aktivitetsniveau.

        updateMacros();
        updateView();
        createDBarray();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMacros();
        updateView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    public void updateView(){
        cRing.setProgress((int) Math.round(Settings.getCurrentPupil().getCalories()));
        pProgress.setProgress((int) Math.round(Settings.getCurrentPupil().getProtein()));
        cProgress.setProgress((int) Math.round(Settings.getCurrentPupil().getCarbs()));
        fProgress.setProgress((int) Math.round(Settings.getCurrentPupil().getFat()));
        caloriesTextView.setText("Calories: \n" +  cRing.getProgress() + "  /  " + cRing.getMax());
        proteinTextView.setText("Protein: " + pProgress.getProgress() +  "  /  " + pProgress.getMax() + " g");
        carbsTextView.setText("Carbs: "+ cProgress.getProgress() + "  /  " + cProgress.getMax() + " g");
        fatTextView.setText("Fat: " + fProgress.getProgress() + "  /  " + fProgress.getMax() + " g");
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

        //bruges til autoText inde i nutrition
        foodAutoText = new String[foodDB.size()];
        for (int i = 0; i < foodDB.size(); i++) {
            foodAutoText[i] = foodDB.get(i).getName();
        }
    }

    public void updateMacros() {
        if (Settings.getCurrentPupil().getGender().equals("male")){
            cRing.setMax((int) Math.round((10 * Settings.getCurrentPupil().getWeight()) + (6.25 * Settings.getCurrentPupil().getHeight())
                    - (5 * Settings.getCurrentPupil().getAge()) + 5) + (Settings.getCurrentPupil().getActivityLevel() * 150));
        }
        else if (Settings.getCurrentPupil().getGender().equals("female")) {
            cRing.setMax((int) Math.round((10 * Settings.getCurrentPupil().getWeight()) + (6.25 * Settings.getCurrentPupil().getHeight())
                    - (5 * Settings.getCurrentPupil().getAge()) - 161) + (Settings.getCurrentPupil().getActivityLevel() * 150));
        }
        pProgress.setMax((int) Math.round ((cRing.getMax()*0.25)/4));
        cProgress.setMax((int) Math.round ((cRing.getMax()*0.5)/4));
        fProgress.setMax((int) Math.round ((cRing.getMax()*0.25)/9));
        Log.d(Settings.getUsers().toString(), "updateMacros: Users");
    }

    //TODO: kan slettes evt
    /*
    public void eatFood(String food){
        Food foodToEat = accessDatabase(food);
        Settings.getCurrentPupil().setCalories(Settings.getCurrentPupil().getCalories() + foodToEat.getCalories());
        Settings.getCurrentPupil().setProtein(Settings.getCurrentPupil().getProtein() + foodToEat.getProtein());
        Settings.getCurrentPupil().setCarbs(Settings.getCurrentPupil().getCarbs() + foodToEat.getCarbs());
        Settings.getCurrentPupil().setFat(Settings.getCurrentPupil().getFat() + foodToEat.getFat());
        cRing.setProgress((int) Math.round(Settings.getCurrentPupil().getCalories()));
        pProgress.setProgress((int) Math.round(Settings.getCurrentPupil().getProtein()));
        cProgress.setProgress((int) Math.round(Settings.getCurrentPupil().getCarbs()));
        fProgress.setProgress((int) Math.round(Settings.getCurrentPupil().getFat()));
    }
     */

    //TODO: kan slettes evt
    /*
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

     */


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("destroy!");
    }
}
