package com.gr_b07;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewDinnerTest extends AbstractMealActivity {
    protected TextView textViewHeader;
    protected TextView tvFood0;
    protected TextView tvFood1;
    protected TextView tvFood2;
    protected TextView tvFood3;
    protected TextView tvFood4;
    protected Food chosenFood;
    protected EditText searchFoodEditText;
    protected Button searchFoodButton;
    protected Button addFoodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_template);
        tvFood0 = findViewById(R.id.tvFood0);
        tvFood1 = findViewById(R.id.tvFood1);
        tvFood2 = findViewById(R.id.tvFood2);
        tvFood3 = findViewById(R.id.tvFood3);
        tvFood4 = findViewById(R.id.tvFood4);
        textViewHeader = findViewById(R.id.textViewHeader);
        searchFoodEditText = findViewById(R.id.searchFoodEditText);
        searchFoodButton = findViewById(R.id.searchFoodButton);
        searchFoodButton.setOnClickListener(this);
        addFoodButton = findViewById(R.id.addMealButton);
        addFoodButton.setOnClickListener(this);

        textViewHeader.setText("Denne her Dinner");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchFoodButton:
                chosenFood = accessDatabase(searchFoodEditText.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                break;
            case R.id.addMealButton:
                if (chosenFood != null) {
                    eatFood(chosenFood);
                    Toast.makeText(this, "Meal added", Toast.LENGTH_SHORT).show();
                }
                Log.d("Cals ", "" + Settings.getCurrentUser().getCalories());
                break;
        }
    }

    @Override
    public void setTextViews(Food food) {
        tvFood0.setText(food.getName());
        tvFood1.setText("Calories: "+food.getCalories());
        tvFood2.setText("Protein: "+food.getProtein());
        tvFood3.setText("Carbs: "+food.getCarbs());
        tvFood4.setText("Fat: "+food.getFat());
    }

    @Override
    public void eatFood(Food food) {
        super.eatFood(food);
    }

    @Override
    public Food accessDatabase(String food) {
        return super.accessDatabase(food);
    }
}
