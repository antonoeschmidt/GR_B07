package com.gr_b07.games.redeemRewards;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.gr_b07.R;

import net.glxn.qrgen.android.QRCode;

/**
 * Pop up window logics taken from/inspired by Filip Vujovic:
 * https://www.youtube.com/watch?v=fn5OlqQuOCk
 */

public class rewardsPopUpActivity extends AppCompatActivity {

    ImageView rewardQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_pop_up);

        rewardQRcode = findViewById(R.id.rewardQRcode);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8),(int) (height * 0.8));

        rewardQRcode.getLayoutParams().width = (int) (width * 0.5);
        rewardQRcode.getLayoutParams().height = (int) (height * 0.5);


        Bitmap QRtest = QRCode.from("www.google.com").bitmap();
        rewardQRcode.setImageBitmap(QRtest);

    }
}
