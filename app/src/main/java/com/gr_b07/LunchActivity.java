package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LunchActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewLunch;
    private TextView tvLunch0;
    private TextView tvLunch1;
    private TextView tvLunch2;
    private TextView tvLunch3;
    private TextView tvLunch4;
    private EditText searchFoodEditText;
    private Button searchFoodButton;
    private Button addFoodButton;
    private InputStream inputStream;
    private String[] data;
    private Food chosenFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        textViewLunch = findViewById(R.id.textViewLunch);
        searchFoodEditText = findViewById(R.id.searchFoodEditText);
        searchFoodButton = findViewById(R.id.searchFoodButtonLunch);
        searchFoodButton.setOnClickListener(this);
        addFoodButton = findViewById(R.id.addMealButtonLunch);
        addFoodButton.setOnClickListener(this);
        tvLunch0 = findViewById(R.id.tvLunch0);
        tvLunch1 = findViewById(R.id.tvLunch1);
        tvLunch2 = findViewById(R.id.tvLunch2);
        tvLunch3 = findViewById(R.id.tvLunch3);
        tvLunch4 = findViewById(R.id.tvLunch4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchFoodButtonLunch:
                chosenFood = accessDatabase(searchFoodEditText.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                break;
            case R.id.addMealButtonLunch:
                if (chosenFood != null) {
                    eatFood(chosenFood);
                    Toast.makeText(this,"Meal added",Toast.LENGTH_SHORT).show();
                }
                Log.d("Cals ", ""+Settings.getCurrentUser().getCalories());
                break;
        }
    }

    private void setTextViews(Food food) {
        tvLunch0.setText(food.getName());
        tvLunch1.setText("Calories: "+food.getCalories());
        tvLunch2.setText("Protein: "+food.getProtein());
        tvLunch3.setText("Carbs: "+food.getCarbs());
        tvLunch4.setText("Fat: "+food.getFat());
    }

    public void eatFood(Food food){
        Settings.getCurrentUser().setCalories(Settings.getCurrentUser().getCalories()+food.getCalories());
        Settings.getCurrentUser().setProtein(Settings.getCurrentUser().getProtein()+food.getProtein());
        Settings.getCurrentUser().setCarbs(Settings.getCurrentUser().getCarbs()+food.getCarbs());
        Settings.getCurrentUser().setFat(Settings.getCurrentUser().getFat()+food.getFat());
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
