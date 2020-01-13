package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gr_b07.R;
import com.gr_b07.logik.Reward;
import com.gr_b07.logik.Settings;

public class RedeemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);

        for(Reward reward: Settings.getCurrentPupil().getRewards()) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutRedeem);
            Button reward1 = new Button(this);
            reward1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            reward1.setText("Prize");
            layout.addView(reward1);
        }

    }
}
