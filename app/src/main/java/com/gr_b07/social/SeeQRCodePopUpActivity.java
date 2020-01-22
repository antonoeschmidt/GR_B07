package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.gr_b07.R;
import com.gr_b07.logik.Settings;

import net.glxn.qrgen.android.QRCode;

public class SeeQRCodePopUpActivity extends AppCompatActivity {

    private TextView socialQRcodeTextView;
    private ImageView socialQRcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_qrcode_pop_up);
        socialQRcodeTextView = findViewById(R.id.socialQRcodeTextView);


        socialQRcode = findViewById(R.id.socialQRcode);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8),(int) (height * 0.8));

        socialQRcode.getLayoutParams().width = (int) (width * 0.5);
        socialQRcode.getLayoutParams().height = (int) (height * 0.5);

        Bitmap QRtest = QRCode.from(Settings.getCurrentUser().getUID()).bitmap();
        socialQRcode.setImageBitmap(QRtest);

        socialQRcodeTextView.setText(Settings.getCurrentPupil().getUID());
    }
}
