package com.gr_b07.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.logik.Food;
import com.gr_b07.logik.Settings;

public class BreakfastActivity extends AbstractMealActivity {
    protected TextView textViewHeader;
    protected TextView tvFood0;
    protected TextView tvFood1;
    protected TextView tvFood2;
    protected TextView tvFood3;
    protected TextView tvFood4;
    protected Food chosenFood;
    protected Button searchFoodButton;
    protected Button addFoodButton;
    protected AutoCompleteTextView autoTextView;

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
        searchFoodButton = findViewById(R.id.searchFoodButton);
        searchFoodButton.setOnClickListener(this);
        addFoodButton = findViewById(R.id.addMealButton);
        addFoodButton.setOnClickListener(this);

        //autocomplete
        autoTextView = findViewById(R.id.autoTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.select_dialog_item, NutritionActivity.foodAutoText);
        autoTextView.setThreshold(1);
        autoTextView.setAdapter(adapter);
        autoTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chosenFood = accessDatabase(autoTextView.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                Settings.hideSoftKeyboard(BreakfastActivity.this);
            }
        });
        textViewHeader.setText("Breakfast");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchFoodButton:
                chosenFood = accessDatabase(autoTextView.getText().toString());
                if (chosenFood != null) {
                    setTextViews(chosenFood);
                }
                break;
            case R.id.addMealButton:
                if (chosenFood != null) {
                    eatFood(chosenFood, textViewHeader.getText().toString());
                    Toast.makeText(this, "Meal added", Toast.LENGTH_SHORT).show();
                }
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
    public void eatFood(Food food, String typeOfMeal) {
        super.eatFood(food, typeOfMeal);
    }

    @Override
    public Food accessDatabase(String food) {
        return super.accessDatabase(food);
    }
}
