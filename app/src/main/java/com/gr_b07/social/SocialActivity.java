package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Settings;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewAccountPhoto;
    private Button buttonGetFriends, buttonAddActivity, buttonSeeQRcode;
    private TextView friendsTextView, suggestedFriendsTextView;
    FB fb = new FB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        buttonSeeQRcode = findViewById(R.id.buttonSeeQRcode);
        buttonSeeQRcode.setOnClickListener(this);
        imageViewAccountPhoto = findViewById(R.id.imageViewAccountPhoto);
        buttonAddActivity = findViewById(R.id.buttonAddActivity);
        buttonAddActivity.setOnClickListener(this);
        friendsTextView = findViewById(R.id.friendsTextView);
        suggestedFriendsTextView = findViewById(R.id.friendsTextView);



        buttonGetFriends = findViewById(R.id.buttonGetFriends);//TODO: Delet
        buttonGetFriends.setOnClickListener(this);


        Settings.getUsers().clear();
        fb.getAllUsersFromDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSeeQRcode:
                Intent seeQRcodeIntent = new Intent(this, SeeQRCodePopUpActivity.class);
                startActivity(seeQRcodeIntent);
                break;
            case R.id.buttonAddActivity:
                Intent addActivityIntent = new Intent(this,AddActivityPopUpActivity.class);
                startActivity(addActivityIntent);
                break;
        }

    }
}
