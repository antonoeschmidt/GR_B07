package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.games.ImageViewScrolling.ImageViewScrolling;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Reward;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.RewardItems;

import java.util.Random;
import java.util.Set;

public class SlotMachineActivity extends AppCompatActivity implements ImageViewScrolling.IEventEnd {

    Button startSlotButton;
    ImageView buttonUp, buttonDown;
    ImageViewScrolling image, image2, image3;
    TextView tickets;
    FB fb = new FB();

    int countDone = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        startSlotButton = findViewById(R.id.startSlotButton);

        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);

        image = findViewById(R.id.image);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        tickets = findViewById(R.id.ticketsSlotMachine);
        tickets.setText("Lodder: " + Settings.getCurrentPupil().getExperience().getTicket());

        image.setEventEnd(SlotMachineActivity.this);
        image2.setEventEnd(SlotMachineActivity.this);
        image3.setEventEnd(SlotMachineActivity.this);

        startSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Settings.getCurrentPupil().getExperience().getTicket() >= 1) {
                    buttonUp.setVisibility(View.GONE);
                    buttonDown.setVisibility(View.VISIBLE);

                    image.setValueRandom(new Random().nextInt(6), new Random().nextInt((15 - 5) + 1) + 5);
                    image2.setValueRandom(new Random().nextInt(6), new Random().nextInt((15 - 5) + 1) + 5);
                    image3.setValueRandom(new Random().nextInt(6), new Random().nextInt((15 - 5) + 1) + 5);

                    Settings.getCurrentPupil().getExperience().setTicket(Settings.getCurrentPupil().getExperience().getTicket() - 1);

                    tickets.setText("Lodder: " + Settings.getCurrentPupil().getExperience().getTicket());

                } else {
                    Toast.makeText(SlotMachineActivity.this,"Du har ikke nogen lodder :/", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void eventEnd(int result, int count) {
        if (countDone < 2) {
            countDone++;
        } else {
            buttonDown.setVisibility(View.GONE);
            buttonUp.setVisibility(View.VISIBLE);
            countDone = 0;
            if (image.getValue() == image2.getValue() && image2.getValue() == image3.getValue()) {
                Toast.makeText(SlotMachineActivity.this, "Stor pris", Toast.LENGTH_SHORT).show();

                //SlotMachineLogic.tickets += 5;
                Settings.getCurrentPupil().addReward(RewardItems.bigPrize);
            } else if (image.getValue() == image2.getValue() || image2.getValue() == image3.getValue() || image.getValue() == image3.getValue()) {
                Toast.makeText(SlotMachineActivity.this, "Lille pris", Toast.LENGTH_SHORT).show();
                //SlotMachineLogic.tickets += 2;
                Settings.getCurrentPupil().addReward(RewardItems.smallPrize);

            } else {
                Toast.makeText(SlotMachineActivity.this, "Du taber", Toast.LENGTH_SHORT).show();
            }
            fb.updateDatabase();
        }
        try {
            Log.d(Settings.getCurrentPupil().getRewards().toString(), "44444");
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }
}
