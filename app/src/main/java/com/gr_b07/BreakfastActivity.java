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

public class BreakfastActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewBreakfast;
    private TextView tvBreakfast0;
    private TextView tvBreakfast1;
    private TextView tvBreakfast2;
    private TextView tvBreakfast3;
    private TextView tvBreakfast4;
    private EditText searchFoodEditText;
    private Button searchFoodButton;
    private Button addFoodButton;
    private InputStream inputStream;
    private String[] data;
    private Food chosenFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        textViewBreakfast = findViewById(R.id.textViewBreakfast);
        searchFoodEditText = findViewById(R.id.searchFoodEditText);
        searchFoodButton = findViewById(R.id.searchFoodButtonBreakfast);
        searchFoodButton.setOnClickListener(this);
        addFoodButton = findViewById(R.id.addMealButtonBreakfast);
        addFoodButton.setOnClickListener(this);
        tvBreakfast0 = findViewById(R.id.tvBreakfast0);
        tvBreakfast1 = findViewById(R.id.tvBreakfast1);
        tvBreakfast2 = findViewById(R.id.tvBreakfast2);
        tvBreakfast3 = findViewById(R.id.tvBreakfast3);
        tvBreakfast4 = findViewById(R.id.tvBreakfast4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchFoodButtonBreakfast:
                chosenFood = accessDatabase(searchFoodEditText.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                break;
            case R.id.addMealButtonBreakfast:
                eatFood(chosenFood);
                Log.d("Cals ", ""+Settings.getCurrentUser().getCalories());
                Toast.makeText(this,"Meal added",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setTextViews(Food food) {
        tvBreakfast0.setText(food.getName());
        tvBreakfast1.setText("Calories: "+food.getCalories());
        tvBreakfast2.setText("Protein: "+food.getProtein());
        tvBreakfast3.setText("Carbs: "+food.getCarbs());
        tvBreakfast4.setText("Fat: "+food.getFat());
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
