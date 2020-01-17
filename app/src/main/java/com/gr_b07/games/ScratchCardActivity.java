/**
 * Scratchcard repo and logics taken from/inspired by
 * https://www.youtube.com/watch?v=_C4mD5jmEgQ
 */

package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;
import com.gr_b07.R;

public class ScratchCardActivity extends AppCompatActivity {

    ScratchImageView scratchImageView, scratchImageView2, scratchImageView3;
    TextView scratchCardTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card);
        scratchCardTextView = findViewById(R.id.scratchCardText);
        scratchCardTextView.setText("0% Revealed");
        scratchImageView = findViewById(R.id.scratchCardView);
        scratchImageView2 = findViewById(R.id.scratchCardView2);
        scratchImageView3 = findViewById(R.id.scratchCardView3);
        //Image1 callback
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {
                Toast.makeText(ScratchCardActivity.this, "you win lol", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
            scratchCardTextView.setText(Math.round(percent * 100) + "% Revealed");
            }
        });
        //Image2 Call back
        scratchImageView2.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {

            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {

            }
        });
        //Image 3 call back
        scratchImageView3.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {

            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {

            }
        });


    }
}
