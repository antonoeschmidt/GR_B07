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
    private Calendar c;
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


        // TODO: fix code
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
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dateTextView.setText(String.format("%02d",day) + "/" + String.format("%02d", (month+1)) + "/" + String.format("%02d", year));
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
                Settings.getCurrentPupil().getPhysique().setHeight(Double.parseDouble(editTextHeight.getText().toString()));
                editTextHeight.setText(editTextHeight.getText().toString());
                Settings.getCurrentPupil().getPhysique().setWeight(Double.parseDouble(editTextWeigth.getText().toString()));
                editTextWeigth.setText(editTextWeigth.getText().toString());

                Settings.getCurrentPupil().getPhysique().setActivityLevel((int) Math.round(activityLevelRatingBar.getRating()));

                Date dateOfBirthDate = df.parse(dateTextView.getText().toString());
                Settings.getCurrentPupil().getPersonalInfo().setDateOfBirth(dateOfBirthDate.getTime());

                //Settings.getCurrentPupil().getPersonalInfo().getAge());


                if (maleRadioButton.isChecked()){
                    Settings.getCurrentPupil().getPersonalInfo().setGender("male");
                } else if (femaleRadioButton.isChecked()){
                    Settings.getCurrentPupil().getPersonalInfo().setGender("female");
                }

                Settings.getCurrentUser().setFirstTimeLoggedIn(false);

                fb.updateDatabase();

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
        if (Settings.getCurrentPupil().getPersonalInfo().getGender().equals("male")) {
            maleRadioButton.toggle();
        } else if (Settings.getCurrentPupil().getPersonalInfo().getGender().equals("female")){
            femaleRadioButton.toggle();
        } else  if (Settings.getCurrentPupil().getPersonalInfo().getGender().equals("n")){
            radioGroup.clearCheck();
        }

        if ((int) Math.round(Settings.getCurrentPupil().getPhysique().getHeight()) == 0){
            editTextHeight.setText("");
            editTextHeight.setHint("Enter height");
        } else if (Settings.getCurrentPupil().getPhysique().getHeight() != 0){
            editTextHeight.setText(Integer.toString((int) Math.round(Settings.getCurrentPupil().getPhysique().getHeight())));
        }

        if ((int) Math.round(Settings.getCurrentPupil().getPhysique().getWeight()) == 0){
            editTextWeigth.setText("");
            editTextWeigth.setHint("Enter weight.");
        } else if (Settings.getCurrentPupil().getPhysique().getWeight() != 0 ) {
            editTextWeigth.setText(Integer.toString((int) Math.round(Settings.getCurrentPupil().getPhysique().getWeight())));
        }

        if (Settings.getCurrentPupil().getPersonalInfo().getDateOfBirth() == 0){
            dateTextView.setText("Enter date of birth");
        } else {
            dateTextView.setText(df.format(Settings.getCurrentPupil().getPersonalInfo().getDateOfBirth()));
        }

        if (Settings.getCurrentPupil().getPhysique().getActivityLevel() == 0){
            activityLevelRatingBar.setRating(0);
        } else if (Settings.getCurrentPupil().getPhysique().getActivityLevel() != 0) {
            activityLevelRatingBar.setRating(Settings.getCurrentPupil().getPhysique().getActivityLevel());
            updateActivityLevelView();
        }
    }
}
