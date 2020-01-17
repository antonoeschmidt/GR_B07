package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;
import com.gr_b07.R;

public class ScratchCardActivity extends AppCompatActivity {

    ScratchImageView scratchImageView;
    TextView scratchCardTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card);
        scratchCardTextView = findViewById(R.id.scratchCardText);
        scratchImageView = new ScratchImageView(this);
        scratchImageView = findViewById(R.id.scratchCardView);
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {
                Toast.makeText(ScratchCardActivity.this, "you win lol", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
                Toast.makeText(ScratchCardActivity.this, "you win lol", Toast.LENGTH_SHORT).show();
            scratchCardTextView.setText(percent + "% Revealed");
            }
        });
    }
}
