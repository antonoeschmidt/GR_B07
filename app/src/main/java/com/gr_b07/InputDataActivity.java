package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InputDataActivity extends AppCompatActivity {
    TextView textView1;
    //RadioGroup radioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        textView1.findViewById(R.id.textView1);
        //radioGroup.findViewById(R.id.radioGroup);
        maleRadioButton.findViewById(R.id.maleRadioButton);
        femaleRadioButton.findViewById(R.id.femaleRadioButton);

    }
}
