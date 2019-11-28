package com.gr_b07;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputDataActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView infoTextView;
    private TextView dateTextView;
    private RadioGroup radioGroup;
    protected RadioButton maleRadioButton, femaleRadioButton;
    protected EditText editTextHeight, editTextWeigth;
    private TextView textViewCM;
    private TextView textViewKG;
    private Button doneButton;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        infoTextView = findViewById(R.id.infoTextView);
        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setOnClickListener(this);

        radioGroup = findViewById(R.id.radioGroup);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeigth = findViewById(R.id.editTextWeight);
        textViewCM = findViewById(R.id.textViewCM);
        textViewKG = findViewById(R.id.textViewKG);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);


        // TODO: DELETE THIS. Only here for easier testing
        //nedenstående gør at man ikke kan ændre i sin data - men hurtigere for testing
        dateTextView.setText("01/12/1998");

        //maleRadioButton.toggle();
        //editTextWeigth.setText("76");
        //editTextHeight.setText("184");
        if (((Pupil)Settings.getCurrentPupil()).getGender() != null && Settings.getCurrentPupil().getHeight() != 0
                && Settings.getCurrentPupil().getWeight() != 0) {
            if (((Pupil)Settings.getCurrentPupil()).getGender().equals("male")) {
                maleRadioButton.toggle();
            } else {
                femaleRadioButton.toggle();
            }
            editTextWeigth.setText("" + Settings.getCurrentPupil().getWeight());
            editTextHeight.setText("" + Settings.getCurrentPupil().getHeight());
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateTextView:
                calendarClick();
                break;
            case R.id.doneButton:
                try {
                    doneButtonClick();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    public void calendarClick(){

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
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void doneButtonClick() throws ParseException {
        // Checks if radio button is selected, if not - Toast prints "choose gender" etc.
        if (!maleRadioButton.isChecked() && !femaleRadioButton.isChecked()) {
            Toast.makeText(this, "Choose gender please.", Toast.LENGTH_SHORT).show();
        }
        // If one the radio buttons is selected, jump into next loop
        else if (maleRadioButton.isChecked() || femaleRadioButton.isChecked()) {
            // If date of birth is more than 10 characters long (xx/xx/xxxx), that must mean its still the
            // original text in the box (alternative method but it works)
            if (dateTextView.getText().toString().length() > 10) {
                Toast.makeText(this, "Enter date of birth, please.", Toast.LENGTH_SHORT).show();
            } else if (editTextWeigth.getText().toString().isEmpty() || editTextHeight.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter height and weight, please.", Toast.LENGTH_SHORT).show();
            }
            // else if they're both containing something - set height, weight and calculate body mass index.
            else if (!editTextWeigth.getText().toString().isEmpty() && !editTextHeight.getText().toString().isEmpty()
                    && dateTextView.getText().toString().length() <= 10 && (maleRadioButton.isChecked() || femaleRadioButton.isChecked())) {
                Settings.getCurrentPupil().setHeight(Double.parseDouble(editTextHeight.getText().toString()));
                editTextHeight.setText(editTextHeight.getText().toString());
                Log.d("heigth", "" + Settings.getCurrentPupil().getHeight());
                Settings.getCurrentPupil().setWeight(Double.parseDouble(editTextWeigth.getText().toString()));
                editTextWeigth.setText(editTextWeigth.getText().toString());
                Log.d("weigth", "" + Settings.getCurrentPupil().getWeight());
                Settings.getCurrentPupil().setBmi(Settings.getCurrentPupil().getWeight() /
                        (Math.pow(Settings.getCurrentPupil().getHeight() / 100, 2)));
                Log.d("bmi", "" + Settings.getCurrentPupil().getBmi());
                DecimalFormat df = new DecimalFormat("#.##");
                Toast.makeText(this, "Your body mass index is : " + df.format(Settings.getCurrentPupil().getBmi()),
                        Toast.LENGTH_SHORT).show();

                Log.d(Integer.toString(calculateAge()), "doneButtonClick: AGE ");
                Settings.getCurrentPupil().setAge(calculateAge());


                if (maleRadioButton.isChecked()){
                    ((Pupil)Settings.getCurrentPupil()).setGender("male");
                    maleRadioButton.toggle();
                }
                else if (femaleRadioButton.isChecked()){
                    ((Pupil)Settings.getCurrentPupil()).setGender("female");
                    femaleRadioButton.toggle();
                }

                Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
                startActivity(mainMenuIntent);
            }
        }
    }

    public int calculateAge() throws NumberFormatException, ParseException {
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date now = new Date(System.currentTimeMillis());
        Settings.getCurrentPupil().setDateOfBirth(f1.parse(dateTextView.getText().toString()));
        long timeBetween = now.getTime() - Settings.getCurrentPupil().getDateOfBirth().getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        int age = (int) Math.floor(yearsBetween);
        Log.d(Integer.toString(age), "doneButtonClick: AGE");
        return age;

    }

        /*
        //int d1 = Integer.parseInt(f1.format(Settings.getCurrentPupil().getDateOfBirth()));
                //int d2 = Integer.parseInt(f2.format(System.currentTimeMillis()));

                int d1 = 100;
                int d2 = 100;
                Log.d(Integer.toString(d1), "doneButtonClick: int1");
                Log.d(Integer.toString(d2), "doneButtonClick: int2");
                //int age = (d2-d1) / 10000;
                //Log.d(Integer.toString(age), "doneButtonClick: ");
                //Log.d(Integer.toString(calculateAge(Settings.getCurrentPupil().getDateOfBirth())), "doneButtonClick: ");
         */

}
