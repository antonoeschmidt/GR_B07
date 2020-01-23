package com.gr_b07.games.redeemRewards;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.gr_b07.R;

import net.glxn.qrgen.android.QRCode;

/**
 * Pop up window logics taken from/inspired by Filip Vujovic:
 * https://www.youtube.com/watch?v=fn5OlqQuOCk
 */

public class RewardsPopUpActivity extends AppCompatActivity {

    protected TextView rewardName;
    protected ImageView rewardPhoto, rewardQRcode;
    protected String rewardNameString;
    protected int rewardPhotoResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_pop_up);

        rewardName = findViewById(R.id.rewardName);

        rewardPhoto = findViewById(R.id.rewardPhoto);
        rewardQRcode = findViewById(R.id.rewardQRcode);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8),(int) (height * 0.8));

        Bundle b = getIntent().getExtras();
        rewardNameString = b.getString("rewardName");
        rewardPhotoResource = b.getInt("rewardPhoto");

        rewardName.setText(rewardNameString);
        rewardPhoto.setImageResource(rewardPhotoResource);

        Bitmap QRtest = QRCode.from(rewardNameString).bitmap();
        rewardQRcode.setImageBitmap(QRtest);

    }
}
