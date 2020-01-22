package com.gr_b07.games.redeemRewards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.gr_b07.R;
import com.gr_b07.logik.Reward;
import com.gr_b07.logik.Settings;

import java.util.ArrayList;

public class RedeemActivity extends AppCompatActivity {

    FrameLayout layout_redeem;
    RecyclerView recyclerView;
    private ArrayList<String> prizeNames = new ArrayList<>();
    private ArrayList<Integer> prizeImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);
        initPrizes();
        layout_redeem = findViewById(R.id.frameLayoutRedeem);
        layout_redeem.getForeground().setAlpha(0);
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
        RecyclerViewAdapterRedeem adapter = new RecyclerViewAdapterRedeem(prizeNames,prizeImages,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        layout_redeem.getForeground().setAlpha(220);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout_redeem.getForeground().setAlpha(0);
    }
}
