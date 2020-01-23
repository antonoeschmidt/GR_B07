package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

public class ScanQRPopUpActivity extends AppCompatActivity {

    private Button buttonScan;
    final Activity activity = this;
    FB fb = new FB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrpop_up);
        scan(this);
        buttonScan = findViewById(R.id.buttonScan);
        fb.getAllUsersFromDatabase();
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan(activity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                if (result.getContents().length() == 28){
                    Settings.getCurrentPupil().getFriends().add(result.getContents());
                    Intent socialIntent = new Intent(this, SocialActivity.class);
                    startActivity(socialIntent);
                } else{

                }

                /*
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                System.out.println(result.getContents());
                if (!Settings.getCurrentPupil().getFriends().isEmpty()){
                    if (Settings.getCurrentPupil().getFriends().contains(result.getContents())){
                        System.out.println("I er allerede venner");
                    } else if (!Settings.getCurrentPupil().getFriends().contains(result.getContents())){
                        Settings.getCurrentPupil().getFriends().add(result.getContents());
                    }
                } else if (Settings.getCurrentPupil().getFriends().isEmpty()){
                    Settings.getCurrentPupil().getFriends().add(result.getContents());
                }*/
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void scan(Activity activty) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
}
