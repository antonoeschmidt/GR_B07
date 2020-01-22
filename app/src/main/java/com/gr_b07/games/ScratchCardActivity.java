package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;
import com.gr_b07.R;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.RewardItems;

import java.util.ArrayList;
import java.util.Random;

/**
 * Scratchcard repo and logics taken from/inspired by
 * https://www.youtube.com/watch?v=_C4mD5jmEgQ
 */


public class ScratchCardActivity extends AppCompatActivity implements View.OnClickListener {

    protected ScratchImageView scratchImageView;
    protected TextView scratchCardTextView, tickets;
    protected float revealedPercent;
    protected Button redeemButton;
    protected Random random = new Random();
    protected int chosenCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card);
        scratchCardTextView = findViewById(R.id.scratchCardText);
        tickets = findViewById(R.id.ticketsScratchCard);
        scratchCardTextView.setText("0% Revealed");
        tickets.setText("Tickets: " + Settings.getCurrentPupil().getExperience().getTicket());
        redeemButton = findViewById(R.id.redeemButton);
        redeemButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (Settings.getCurrentPupil().getExperience().getTicket() > 0) {
            Settings.getCurrentPupil().getExperience().setTicket(Settings.getCurrentPupil().getExperience().getTicket() - 1);
            redeemButton.setVisibility(View.INVISIBLE);
            updateTextView();
            initScratchCard();
        } else {
            Toast.makeText(this, "Du har ikke nok tickets.", Toast.LENGTH_SHORT).show();
        }

    }

    private void initScratchCard() {
        scratchImageView = findViewById(R.id.scratchCardView);
        scratchImageView.setVisibility(View.VISIBLE);
        scratchImageView.setImageResource(chooseScratchCard());
        //Image1 callback
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView iv) {
                if (chosenCard == R.drawable.scratch_card_image_lose) {
                    Toast.makeText(ScratchCardActivity.this, "Ingen gevinst", Toast.LENGTH_SHORT).show();
                }
                if (chosenCard == R.drawable.scratch_card_image_win_small) {
                    Toast.makeText(ScratchCardActivity.this, "You win a kombucha lol", Toast.LENGTH_SHORT).show();
                    Settings.getCurrentPupil().addReward(RewardItems.smallPrize);
                }
                if (chosenCard == R.drawable.scratch_card_image_win_big) {
                    Toast.makeText(ScratchCardActivity.this, "You win a cykel lol", Toast.LENGTH_SHORT).show();
                    Settings.getCurrentPupil().addReward(RewardItems.bigPrize);
                }
            }

            @Override
            public void onRevealPercentChangedListener(ScratchImageView siv, float percent) {
                if (percent > 0.5) {
                    scratchImageView.clear();
                }
                revealedPercent = percent;
                updateTextView();
            }
        });
    }

    private void updateTextView() {
        scratchCardTextView.setText(Math.round(revealedPercent * 100) + "% Revealed");
        tickets.setText("Tickets: " + Settings.getCurrentPupil().getExperience().getTicket());
    }

    private int chooseScratchCard() {
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
