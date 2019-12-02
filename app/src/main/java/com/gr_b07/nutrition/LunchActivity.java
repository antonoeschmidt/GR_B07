package com.gr_b07.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.NutritionActivity;
import com.gr_b07.R;
import com.gr_b07.logik.Food;
import com.gr_b07.logik.Settings;

public class LunchActivity extends BreakfastActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewHeader.setText("Lunch");
    }
}