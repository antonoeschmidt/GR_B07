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

public class SlotMachineActivity extends AppCompatActivity implements IEventEnd {

    ImageView buttonUp, buttonDown;
    ImageViewScrolling image, image2, image3;
    TextView tickets;

    int countDone = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);

        buttonUp.findViewById(R.id.buttonUp);
        buttonDown.findViewById(R.id.buttonDown);

        image.findViewById(R.id.image);
        image2.findViewById(R.id.image2);
        image3.findViewById(R.id.image3);

        tickets.findViewById(R.id.tickets);

        image.setEventEnd(SlotMachineActivity.this);
        image2.setEventEnd(SlotMachineActivity.this);
        image3.setEventEnd(SlotMachineActivity.this);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SlotMachineLogic.tickets >= 1){

                } else {
                    Toast.makeText(SlotMachineActivity.this,"Du har ikke nogen tickets :/", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void eventEnd(int result, int count) {

    }
}
