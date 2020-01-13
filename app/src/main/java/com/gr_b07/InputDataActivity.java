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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.gr_b07.logik.FB;
import com.gr_b07.logik.Settings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InputDataActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView infoTextView, textViewCM, textViewKG, dateTextView, activityLevelTextView;
    private RadioGroup radioGroup;
    protected RadioButton maleRadioButton, femaleRadioButton;
    protected EditText editTextHeight, editTextWeigth;
    private Button doneButton;
    private Calendar calendar;
    private RatingBar activityLevelRatingBar;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private FB fb = new FB();

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
        activityLevelRatingBar = findViewById(R.id.activityLevelRatingBar);
        activityLevelRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                updateActivityLevelView();
                Log.d(String.valueOf(activityLevelRatingBar.getRating()), "onRatingChanged: Hejmeddig");

            }
        });
        activityLevelTextView = findViewById(R.id.activityLevelTextView);
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(this);


        // TODO: DELETE THIS. Only here for easier testing
        getUserInfo();
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
            case R.id.activityLevelRatingBar:
                updateActivityLevelView();
                Log.d(Integer.toString(activityLevelRatingBar.getProgress()), "onClick: Hey");
        }
    }


    public void calendarClick(){

        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR)-13;
        Log.d(Integer.toString(day), "calendarClick: ");
        Log.d(Integer.toString(month), "calendarClick: ");
        Log.d(Integer.toString(year), "calendarClick: ");

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (dayOfMonth < 10 && (month+1) < 10) {
                            dateTextView.setText("0" + dayOfMonth + "/" + "0" + (month+1) + "/" + year);
                        } else if (dayOfMonth >= 10 && (month+1) < 10) {
                            dateTextView.setText(dayOfMonth + "/" + "0" + (month+1) + "/" + year);
                        } else if (dayOfMonth < 10 && (month+1) >= 10) {
                            dateTextView.setText("0" + dayOfMonth + "/" + (month+1) + "/" + year);
                        } else if (dayOfMonth >= 10 && (month+1) >= 10){
                            dateTextView.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                        }
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
            } else if (activityLevelRatingBar.getRating()==0){
                Toast.makeText(this, "Enter activity-level please.", Toast.LENGTH_SHORT).show();
        }
            // else if they're both containing something - set height, weight and calculate body mass index.
            else if (!editTextWeigth.getText().toString().isEmpty() && !editTextHeight.getText().toString().isEmpty()
                    && dateTextView.getText().toString().length() <= 10 && (maleRadioButton.isChecked() || femaleRadioButton.isChecked())) {
                Settings.getCurrentPupil().setHeight(Double.parseDouble(editTextHeight.getText().toString()));
                editTextHeight.setText(editTextHeight.getText().toString());
                Settings.getCurrentPupil().setWeight(Double.parseDouble(editTextWeigth.getText().toString()));
                editTextWeigth.setText(editTextWeigth.getText().toString());
                Settings.getCurrentPupil().setBmi(Settings.getCurrentPupil().getWeight() /
                        (Math.pow(Settings.getCurrentPupil().getHeight() / 100, 2)));

                Date blabla = df.parse(dateTextView.getText().toString());
                Log.d(dateTextView.getText().toString().substring(3,5), "substring month: ");
                String month = dateTextView.getText().toString().substring(3,4);

                String.format("%02d",month);





                Settings.getCurrentPupil().setDateOfBirth(df.parse(dateTextView.getText().toString()));

                Settings.getCurrentPupil().setActivityLevel((int) Math.round(activityLevelRatingBar.getRating()));
                Log.d(Integer.toString(Settings.getCurrentPupil().getActivityLevel()), "doneButtonClick: Activitylevel");


                if (maleRadioButton.isChecked()){
                    (Settings.getCurrentPupil()).setGender("male");
                    maleRadioButton.toggle();
                }
                else if (femaleRadioButton.isChecked()){
                    (Settings.getCurrentPupil()).setGender("female");
                    femaleRadioButton.toggle();
                }

                Settings.getCurrentUser().setFirstTimeLoggedIn(false);

                fb.updateDatabase(Settings.getCurrentPupil(),fb.getAuth().getCurrentUser());

                Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
                startActivity(mainMenuIntent);
            }
        }
    }
    public void updateActivityLevelView() {
        if (activityLevelRatingBar.getRating()==0){
            activityLevelRatingBar.setRating(1);
        }
        int i = (int) Math.round(activityLevelRatingBar.getRating());
        if (i<=1) {
            activityLevelTextView.setText("Lavt aktivititetsniveau");
        } else if (i==2) {
            activityLevelTextView.setText("Acceptabelt aktivitetsniveau");
        } else if (i==3) {
            activityLevelTextView.setText("Moderat aktivitetsniveau");
        } else if (i==4) {
            activityLevelTextView.setText("Højt aktivitetsniveau");
        } else if (i==5) {
            activityLevelTextView.setText("Ekstremt aktivitetsniveau");
        }
    }

    public void getUserInfo(){
        if ((Settings.getCurrentPupil()).getGender().equals("male")) {
            maleRadioButton.toggle();
        } else if (Settings.getCurrentPupil().getGender().equals("female")){
            femaleRadioButton.toggle();
        }

        if (Settings.getCurrentPupil().getDateOfBirth()==null){
            dateTextView.setText("Enter date of birth");
        } else {
            dateTextView.setText(df.format(Settings.getCurrentPupil().getDateOfBirth()));
        }

        editTextHeight.setText(Integer.toString((int) Math.round (Settings.getCurrentPupil().getHeight())));
        editTextWeigth.setText(Integer.toString((int) Math.round (Settings.getCurrentPupil().getWeight())));
        activityLevelRatingBar.setRating(Settings.getCurrentPupil().getActivityLevel());
        updateActivityLevelView();
    }
}
