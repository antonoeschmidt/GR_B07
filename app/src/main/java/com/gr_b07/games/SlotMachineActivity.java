package com.gr_b07.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.games.ImageViewScrolling.IEventEnd;
import com.gr_b07.games.ImageViewScrolling.ImageViewScrolling;

import java.util.Random;

public class SlotMachineActivity extends AppCompatActivity implements IEventEnd {

    ImageView buttonUp, buttonDown;
    ImageViewScrolling image, image2, image3;
    TextView tickets;

    int countDone = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);

        image = findViewById(R.id.image);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        tickets = findViewById(R.id.tickets);

        image.setEventEnd(SlotMachineActivity.this);
        image2.setEventEnd(SlotMachineActivity.this);
        image3.setEventEnd(SlotMachineActivity.this);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SlotMachineLogic.tickets >= 1){
                    buttonUp.setVisibility(View.GONE);
                    buttonDown.setVisibility(View.VISIBLE);

                    image.setValueRandom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);
                    image2.setValueRandom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);
                    image3.setValueRandom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);

                    SlotMachineLogic.tickets -= 1;

                    tickets.setText(String.valueOf(SlotMachineLogic.tickets));

                } else {
                    Toast.makeText(SlotMachineActivity.this,"Du har ikke nogen tickets :/", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void eventEnd(int result, int count) {
            if(countDone < 2){
                countDone++;
            } else {
                buttonDown.setVisibility(View.GONE);
                buttonUp.setVisibility(View.VISIBLE);
                countDone = 0;
                if(image.getValue() == image2.getValue() && image2.getValue() == image3.getValue()){
                    Toast.makeText(SlotMachineActivity.this,"Stor pris", Toast.LENGTH_SHORT).show();
                    SlotMachineLogic.tickets += 5;
                    tickets.setText(String.valueOf(SlotMachineLogic.tickets));
                } else if(image.getValue() == image2.getValue() || image2.getValue() == image3.getValue() || image.getValue() == image3.getValue()) {
                    Toast.makeText(SlotMachineActivity.this, "Lille pris", Toast.LENGTH_SHORT).show();
                    SlotMachineLogic.tickets += 2;
                    tickets.setText(String.valueOf(SlotMachineLogic.tickets));
                } else {
                    Toast.makeText(SlotMachineActivity.this, "Du taber", Toast.LENGTH_SHORT).show();
                }
            }


    }
}
