package com.gr_b07.nutrition;

import android.os.Bundle;

public class LunchActivity extends BreakfastActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autoTextView.requestFocus();
        textViewHeader.setText("Frokost");
    }
}