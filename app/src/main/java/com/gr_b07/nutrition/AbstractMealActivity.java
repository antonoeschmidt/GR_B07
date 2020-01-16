package com.gr_b07.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Food;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class AbstractMealActivity extends AppCompatActivity implements View.OnClickListener {
    protected InputStream inputStream;
    protected String[] data;
    private FB fb = new FB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public abstract void onClick(View v);

    public abstract void setTextViews(Food food);

    public Food accessDatabase(String food) {
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

    public void eatFood(Food food, String typeOfMeal){
        //TODO: flyt denne metode til pupil og implementer rewards
        Settings.getCurrentPupil().addMeal(new Meal(food.getName(),food.getCalories(),food.getProtein(),food.getCarbs(),
        food.getFat(),typeOfMeal,System.currentTimeMillis()));
        fb.updateDatabase();


    }
}
