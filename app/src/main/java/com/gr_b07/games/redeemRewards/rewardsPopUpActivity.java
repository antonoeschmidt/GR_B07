/**
 * Pop up window logics taken from/inspired by Filip Vujovic:
 * https://www.youtube.com/watch?v=fn5OlqQuOCk
 */

package com.gr_b07.games.redeemRewards;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.gr_b07.R;

import net.glxn.qrgen.android.QRCode;

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

        Bitmap QRtest = QRCode.from("www.goodle.com").bitmap();
        rewardQRcode.setImageBitmap(QRtest);

    }
}
