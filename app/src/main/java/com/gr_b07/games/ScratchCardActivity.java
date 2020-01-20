package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;
import com.gr_b07.R;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.rewardItems;

import java.util.ArrayList;
import java.util.Random;

/**
 * Scratchcard repo and logics taken from/inspired by
 * https://www.youtube.com/watch?v=_C4mD5jmEgQ
 */


public class ScratchCardActivity extends AppCompatActivity implements View.OnClickListener{

    ScratchImageView scratchImageView;
    TextView scratchCardTextView;
    float revealedPercent;
    Button redeemButton;
    Random random = new Random();
    int chosenCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card);
        scratchCardTextView = findViewById(R.id.scratchCardText);
        scratchCardTextView.setText("0% Revealed");
        redeemButton = findViewById(R.id.redeemButton);
        redeemButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        redeemButton.setVisibility(View.INVISIBLE);
        initScratchCard();
    }

    private void initScratchCard() {
        scratchImageView = findViewById(R.id.scratchCardView);
        scratchImageView.setVisibility(View.VISIBLE);
        scratchImageView.setImageResource(chooseScratchCard());
        //Image1 callback
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {
                if(chosenCard == R.drawable.scratch_card_image_lose){
                    Toast.makeText(ScratchCardActivity.this,"Ingen gevinst",Toast.LENGTH_SHORT).show();
                }
                if(chosenCard == R.drawable.scratch_card_image_win_small){
                    Toast.makeText(ScratchCardActivity.this,"You win a kombucha lol",Toast.LENGTH_SHORT).show();
                    Settings.getCurrentPupil().addReward(rewardItems.smallPrize);
                }
                if(chosenCard == R.drawable.scratch_card_image_win_big){
                    Toast.makeText(ScratchCardActivity.this,"You win a cykel lol",Toast.LENGTH_SHORT).show();
                    Settings.getCurrentPupil().addReward(rewardItems.bigPrize);
                }
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
               if(percent > 0.5){
                   scratchImageView.clear();
               }
                revealedPercent = percent;
                updateTextView();
            }
        });
    }

    private void updateTextView() {
        scratchCardTextView.setText(Math.round(revealedPercent * 100) + "% Revealed");
    }

    private int chooseScratchCard(){
        ArrayList<Integer> chanceNumbers = new ArrayList<>();
        chanceNumbers.add(R.drawable.scratch_card_image_lose);
        chanceNumbers.add(R.drawable.scratch_card_image_lose);
        chanceNumbers.add(R.drawable.scratch_card_image_lose);
        chanceNumbers.add(R.drawable.scratch_card_image_win_small);
        chanceNumbers.add(R.drawable.scratch_card_image_win_small);
        chanceNumbers.add(R.drawable.scratch_card_image_win_big);
        chosenCard = chanceNumbers.get(random.nextInt(chanceNumbers.size()));
        return chosenCard;
    }
}
