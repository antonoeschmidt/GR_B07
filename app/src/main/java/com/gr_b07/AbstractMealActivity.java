package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.gr_b07.NutritionActivity.foodDB;

public abstract class AbstractMealActivity extends AppCompatActivity implements View.OnClickListener {
    protected TextView tvFood0;
    protected TextView tvFood1;
    protected TextView tvFood2;
    protected TextView tvFood3;
    protected TextView tvFood4;
    protected InputStream inputStream;
    protected EditText searchFoodEditText;
    protected Button searchFoodButton;
    protected Button addFoodButton;

    protected String[] data;
    protected Food chosenFood;

    static String[] foodAuto;

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

    public void eatFood(Food food){
        Settings.getCurrentUser().setCalories(Settings.getCurrentUser().getCalories()+food.getCalories());
        Settings.getCurrentUser().setProtein(Settings.getCurrentUser().getProtein()+food.getProtein());
        Settings.getCurrentUser().setCarbs(Settings.getCurrentUser().getCarbs()+food.getCarbs());
        Settings.getCurrentUser().setFat(Settings.getCurrentUser().getFat()+food.getFat());
    }

    public void initAutoCompleter() {
        foodAuto = new String[foodDB.size()];
    }

}
