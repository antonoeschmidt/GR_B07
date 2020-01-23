package com.gr_b07.nutrition;

import android.os.Bundle;

public class SnacksActivity extends BreakfastActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autoTextView.requestFocus();
        textViewHeader.setText("Snacks");
    }
}