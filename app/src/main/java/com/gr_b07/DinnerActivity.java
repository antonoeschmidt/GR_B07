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

public class DinnerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewDinner;
    private TextView tvDinner0;
    private TextView tvDinner1;
    private TextView tvDinner2;
    private TextView tvDinner3;
    private TextView tvDinner4;
    private EditText searchFoodEditText;
    private Button searchFoodButton;
    private Button addFoodButton;
    private InputStream inputStream;
    private String[] data;
    private Food chosenFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner);
        textViewDinner = findViewById(R.id.textViewDinner);
        searchFoodEditText = findViewById(R.id.searchFoodEditText);
        searchFoodButton = findViewById(R.id.searchFoodButtonDinner);
        searchFoodButton.setOnClickListener(this);
        addFoodButton = findViewById(R.id.addMealButtonDinner);
        addFoodButton.setOnClickListener(this);
        tvDinner0 = findViewById(R.id.tvDinner0);
        tvDinner1 = findViewById(R.id.tvDinner1);
        tvDinner2 = findViewById(R.id.tvDinner2);
        tvDinner3 = findViewById(R.id.tvDinner3);
        tvDinner4 = findViewById(R.id.tvDinner4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchFoodButtonDinner:
                chosenFood = accessDatabase(searchFoodEditText.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                break;
            case R.id.addMealButtonDinner:
                if (chosenFood != null) {
                    eatFood(chosenFood);
                    Toast.makeText(this,"Meal added",Toast.LENGTH_SHORT).show();
                }
                Log.d("Cals ", ""+Settings.getCurrentUser().getCalories());
                break;
        }
    }

    private void setTextViews(Food food) {
        tvDinner0.setText(food.getName());
        tvDinner1.setText("Calories: "+food.getCalories());
        tvDinner2.setText("Protein: "+food.getProtein());
        tvDinner3.setText("Carbs: "+food.getCarbs());
        tvDinner4.setText("Fat: "+food.getFat());
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
