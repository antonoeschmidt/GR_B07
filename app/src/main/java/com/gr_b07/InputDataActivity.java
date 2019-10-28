package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class InputDataActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView1;
    private TextView dateTextView;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private EditText editTextHeigth;
    private EditText editTextWeigth;
    private TextView textViewCM;
    private TextView textViewKG;
    private Button doneButton;
    private Calendar calendar;

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
        editTextHeigth = findViewById(R.id.editTextHeight);
        editTextWeigth = findViewById(R.id.editTextWeight);
        textViewCM = findViewById(R.id.textViewCM);
        textViewKG = findViewById(R.id.textViewKG);
        doneButton = findViewById(R.id.doneButton);

    }

    @Override
    public void onClick(View v) {

        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            dateTextView.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}
