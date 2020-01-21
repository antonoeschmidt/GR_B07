package com.gr_b07.nutrition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.gr_b07.R;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;

import java.util.ArrayList;

public class MealsActivity extends AppCompatActivity {

    private FrameLayout layout_meals;
    private RecyclerView recyclerView;
    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<Integer> mealImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        initMeals();
        layout_meals = findViewById(R.id.frameLayoutMeals);
        layout_meals.getForeground().setAlpha(0);


    }

    private void initMeals(){
        for(Meal meal: Settings.getCurrentPupil().getMeals()) {
            meals.add(meal);
            //prizeImages.add(reward.getResource());
            initRecylcerView();
        }
    }

    private void initRecylcerView(){
        recyclerView = findViewById(R.id.recyclerViewMeals);
        RecyclerViewAdapterMeals adapter = new RecyclerViewAdapterMeals(meals, mealImages,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onPause() {
        super.onPause();
        layout_meals.getForeground().setAlpha(220);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout_meals.getForeground().setAlpha(0);
    }
    
}