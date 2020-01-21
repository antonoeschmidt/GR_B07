package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener {

    FB fb = new FB();
    private ImageView imageViewAccountPhoto;
    private Button buttonGetFriends, buttonAddActivity, buttonSeeQRcode, buttonScanQRcode;
    private TextView friendsTextView, suggestedFriendsTextView;


    private RecyclerView friendsRecyclerView;
    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<Integer> userPhotos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);


        buttonSeeQRcode = findViewById(R.id.buttonSeeQRcode);
        buttonSeeQRcode.setOnClickListener(this);

        buttonScanQRcode = findViewById(R.id.buttonScanQRcode);
        buttonScanQRcode.setOnClickListener(this);

        imageViewAccountPhoto = findViewById(R.id.imageViewAccountPhoto);
        buttonAddActivity = findViewById(R.id.buttonAddActivity);
        buttonAddActivity.setOnClickListener(this);
        friendsTextView = findViewById(R.id.friendsTextView);
        suggestedFriendsTextView = findViewById(R.id.suggestedFriendsTextView);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        friendsRecyclerView.setLayoutManager(horizontalLayoutManager);
        //suggestedFriendsRecyclerView = findViewById(R.id.suggestedFriendsRecyclerView);



        buttonGetFriends = findViewById(R.id.buttonGetFriends);//TODO: Delet
        buttonGetFriends.setOnClickListener(this);


        fb.getAllUsersFromDatabase();
        Log.d(Settings.getUsers().toString(), "onCreate: ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSeeQRcode:
                Intent seeQRcodeIntent = new Intent(this, SeeQRCodePopUpActivity.class);
                startActivity(seeQRcodeIntent);
                break;
            case R.id.buttonAddActivity:
                Intent addActivityIntent = new Intent(this, AddActivityPopUpActivity.class);
                startActivity(addActivityIntent);
                break;
            case R.id.buttonGetFriends:
                initFriends();
                break;
            case R.id.buttonScanQRcode:
                Intent scanQRIntent = new Intent(this, ScanQRPopUpActivity.class);
                startActivity(scanQRIntent);
        }

    }

    public void initFriends(){
        for (User user: Settings.getUsers()) {
            if (user.getClass().equals(Pupil.class)
                    && !Settings.getCurrentPupil().getUID().equals(user.getUID())
                    && !Settings.getCurrentPupil().getFriends().contains(user.getUID())
                    && !usernames.contains(user.getUsername())) {
                usernames.add(user.getUsername());
                userPhotos.add(R.drawable.friend_nophoto);
                initializeRecyclerView();
            }
        }
    }

    public void initializeRecyclerView(){
        friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        RecyclerViewAdapterSocial adapter = new RecyclerViewAdapterSocial(usernames, userPhotos, this);
        friendsRecyclerView.setAdapter(adapter);
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
