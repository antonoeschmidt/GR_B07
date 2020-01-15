package com.gr_b07.games.redeemRewards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gr_b07.R;
import com.gr_b07.logik.Reward;
import com.gr_b07.logik.Settings;

import java.util.ArrayList;

public class RedeemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<String> prizeNames = new ArrayList<>();
    private ArrayList<Integer> prizeImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);
        initPrizes();
    }

    private void initPrizes(){
        for(Reward reward: Settings.getCurrentPupil().getRewards()) {
            prizeNames.add(reward.getName());
            prizeImages.add(reward.getResource());
            initRecylcerView();
        }
    }

    private void initRecylcerView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapterRedeem adapter = new recyclerViewAdapterRedeem(prizeNames,prizeImages,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}



/* for(Reward reward: Settings.getCurrentPupil().getRewards()) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayoutRedeem);
            Button reward1 = new Button(this);
            reward1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            reward1.setText("Prize");
            layout.addView(reward1);
        }
*/