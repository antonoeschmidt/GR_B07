package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.Toast;

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
    int height, weight, bmi;

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
        doneButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateTextView:
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
                break;
            case R.id.doneButton:
                // Checks if radio button is selected, if not - Toast prints "choose gender" etc.
                if (!maleRadioButton.isChecked() && !femaleRadioButton.isChecked()){
                    Toast.makeText(this, "Choose gender please.", Toast.LENGTH_SHORT).show();
                }
                // If one radio button is selected, jump into next loop
                else if (maleRadioButton.isChecked() || femaleRadioButton.isChecked()){
                    // If height or weight is empty - Toast prints "enter weight" etc.
                    if (editTextWeigth.getText().toString().isEmpty() || editTextHeigth.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter height/weight please", Toast.LENGTH_SHORT).show();
                    }
                    // else if they're both containing something - set height, weight and calculate body mass index.
                    else if (!editTextWeigth.getText().toString().isEmpty() && !editTextHeigth.getText().toString().isEmpty()){
                        height = Integer.parseInt(editTextHeigth.getText().toString());
                        weight = Integer.parseInt(editTextWeigth.getText().toString());
                        bmi = weight/height^2;
                        Toast.makeText(this, "Your body mass index is : " + bmi, Toast.LENGTH_SHORT).show();
                        Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
                        startActivity(mainMenuIntent);
                    }
                }
        }
    }
}
