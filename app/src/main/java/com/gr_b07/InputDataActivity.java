package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class InputDataActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1;
    TextView dateTextView;
    RadioGroup radioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    DatePickerDialog dpd;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        textView1 = findViewById(R.id.textView1);
        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setOnClickListener(this);
        radioGroup = findViewById(R.id.radioGroup);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);


    }

    @Override
    public void onClick(View v) {

        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                dateTextView.setText(mDay + "/" + mMonth + "/" + mYear);
            }
        },day,month,year);
        dpd.show();
    }
}
