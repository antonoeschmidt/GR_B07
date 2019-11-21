package com.gr_b07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.logik.Settings;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView ticketTextView;
    private Button useTicketButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ticketTextView = findViewById(R.id.textViewTickets);
        useTicketButton = findViewById(R.id.buttonUseTicket);
        useTicketButton.setOnClickListener(this);

        updateTextView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonUseTicket:
                if (Settings.getCurrentUser().getTicket() >= 1){
                    // TODO: Implementér hvad der skal ske ved brug af én ticket. evt flere muligheder.
                    Settings.getCurrentUser().setTicket(Settings.getCurrentUser().getTicket()-1);
                }
                else Toast.makeText(this, "You aint got the tickets hombre", Toast.LENGTH_SHORT).show();
                updateTextView();
                break;

        }
    }
    public void updateTextView(){
        ticketTextView.setText("Tickets: " + Settings.getCurrentUser().getTicket());
    }
}
