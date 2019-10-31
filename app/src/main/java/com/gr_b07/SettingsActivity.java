package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView infoTextView;
    private TextView dateTextView;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private EditText editTextHeigth, editTextWeight;
    private TextView textViewCM;
    private TextView textViewKG;
    private Button doneButton;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        infoTextView = findViewById(R.id.infoTextView);
        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setOnClickListener(this);

        radioGroup = findViewById(R.id.radioGroup);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        editTextHeigth = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        textViewCM = findViewById(R.id.textViewCM);
        textViewKG = findViewById(R.id.textViewKG);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);

        DecimalFormat df1 = new DecimalFormat("###");

        editTextHeigth.setHint((df1.format(Settings.getCurrentUser().getHeight())));
        editTextWeight.setHint(df1.format((Settings.getCurrentUser().getWeight())));
        if (Settings.getCurrentUser().getGender() == 'm'){
            maleRadioButton.toggle();
        }
        else if (Settings.getCurrentUser().getGender() == 'f'){
            femaleRadioButton.toggle();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateTextView:
                //InputDataActivity.calendarClick();
                break;
            case R.id.doneButton:
                // InputDataActivity.doneButtonClick();
                break;
        }
    }
}
