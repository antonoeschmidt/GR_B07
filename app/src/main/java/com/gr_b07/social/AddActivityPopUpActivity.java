package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gr_b07.R;
import com.gr_b07.logik.Settings;

import net.glxn.qrgen.android.QRCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AddActivityPopUpActivity extends AppCompatActivity implements View.OnClickListener {

    private InputStream inputStream;
    private String[] data;

    private TextView textViewHeaderAddActivity;
    private AutoCompleteTextView autoTextViewAddActivity;
    private Button buttonAddActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pop_up);
        textViewHeaderAddActivity = findViewById(R.id.textViewHeaderAddActivity);
        autoTextViewAddActivity = findViewById(R.id.autoTextViewAddActivity);
        buttonAddActivity = findViewById(R.id.buttonAddActivity);
        buttonAddActivity.setOnClickListener(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8),(int) (height * 0.8));
    }

    public String accessActivities(String activity) {
        inputStream = getResources().openRawResource(R.raw.activities);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                data = csvLine.split(";");
                if (data[0].equalsIgnoreCase(activity)) {
                    return (data[0]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Error ", "Cannot read file");
        }
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
