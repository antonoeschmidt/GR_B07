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

public class SnacksActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewSnacks;
    private TextView tvSnacks0;
    private TextView tvSnacks1;
    private TextView tvSnacks2;
    private TextView tvSnacks3;
    private TextView tvSnacks4;
    private EditText searchFoodEditText;
    private Button searchFoodButton;
    private Button addFoodButton;
    private InputStream inputStream;
    private String[] data;
    private Food chosenFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        textViewSnacks = findViewById(R.id.textViewSnacks);
        searchFoodEditText = findViewById(R.id.searchFoodEditText);
        searchFoodButton = findViewById(R.id.searchFoodButtonSnacks);
        searchFoodButton.setOnClickListener(this);
        addFoodButton = findViewById(R.id.addMealButtonSnacks);
        addFoodButton.setOnClickListener(this);
        tvSnacks0 = findViewById(R.id.tvSnacks0);
        tvSnacks1 = findViewById(R.id.tvSnacks1);
        tvSnacks2 = findViewById(R.id.tvSnacks2);
        tvSnacks3 = findViewById(R.id.tvSnacks3);
        tvSnacks4 = findViewById(R.id.tvSnacks4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchFoodButtonSnacks:
                chosenFood = accessDatabase(searchFoodEditText.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                break;
            case R.id.addMealButtonSnacks:
                if (chosenFood != null) {
                    eatFood(chosenFood);
                    Toast.makeText(this,"Meal added",Toast.LENGTH_SHORT).show();
                }
                Log.d("Cals ", ""+Settings.getCurrentUser().getCalories());
                break;
        }
    }

    private void setTextViews(Food food) {
        tvSnacks0.setText(food.getName());
        tvSnacks1.setText("Calories: "+food.getCalories());
        tvSnacks2.setText("Protein: "+food.getProtein());
        tvSnacks3.setText("Carbs: "+food.getCarbs());
        tvSnacks4.setText("Fat: "+food.getFat());
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
