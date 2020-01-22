package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.games.ChanceActivity;
import com.gr_b07.games.ChestActivity;
import com.gr_b07.games.redeemRewards.RedeemActivity;
import com.gr_b07.games.ScratchCardActivity;
import com.gr_b07.games.SlotMachineActivity;
import com.gr_b07.logik.Settings;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView ticketTextView;
    private Button slotMachineButton, scratchCardButton, gameChestButton, gameChanceButton, redeemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ticketTextView = findViewById(R.id.textViewTickets);

        slotMachineButton = findViewById(R.id.buttonGameSlotmachine);
        slotMachineButton.setOnClickListener(this);
        scratchCardButton = findViewById(R.id.buttonGameScratchCard);
        scratchCardButton.setOnClickListener(this);
        gameChestButton = findViewById(R.id.buttonGameChest);
        gameChestButton.setOnClickListener(this);
        gameChanceButton = findViewById(R.id.buttonGameChance);
        gameChanceButton.setOnClickListener(this);
        redeemButton = findViewById(R.id.buttonRedeem);
        redeemButton.setOnClickListener(this);

        updateTextView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonGameSlotmachine:
                Intent slotMachineIntent = new Intent(this, SlotMachineActivity.class);
                startActivity(slotMachineIntent);
                break;

            case R.id.buttonGameScratchCard:
                Intent scratchCardIntent = new Intent(this, ScratchCardActivity.class);
                startActivity(scratchCardIntent);
                break;

            case R.id.buttonGameChest:
                Intent chestIntent = new Intent(this, ChestActivity.class);
                startActivity(chestIntent);
                break;

            case R.id.buttonGameChance:
                Intent chanceIntent = new Intent(this, ChanceActivity.class);
                startActivity(chanceIntent);
                break;
            case R.id.buttonRedeem:
                Intent redeemIntent = new Intent(this, RedeemActivity.class);
                startActivity(redeemIntent);


        }
    }
    public void updateTextView(){
        ticketTextView.setText("Tickets: " + Settings.getCurrentPupil().getExperience().getTicket());
    }
}
