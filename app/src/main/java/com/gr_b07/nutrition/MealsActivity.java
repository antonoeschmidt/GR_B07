package com.gr_b07.nutrition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.SpringClient;

import java.util.ArrayList;

public class MealsActivity extends AppCompatActivity implements MealDialog.DialogFragmentUpdateListener {

    private FrameLayout layout_meals;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMeals adapter;
    private ArrayList<Integer> mealImages = new ArrayList<>();
    private SpringClient springClient = new SpringClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        initRecylcerView();
        layout_meals = findViewById(R.id.frameLayoutMeals);
        layout_meals.getForeground().setAlpha(0);
    }

    private void initRecylcerView() {
        recyclerView = findViewById(R.id.recyclerViewMeals);
        adapter = new RecyclerViewAdapterMeals((ArrayList)Settings.getCurrentPupil().getMeals(), mealImages, this);
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

    @Override
    public void updateMeals() {
        adapter.notifyDataSetChanged();
        //fb.updateDatabase();
        springClient.updateDatabase();
    }
}
