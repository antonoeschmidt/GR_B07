package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity implements RecyclerViewAdapterSocial.ItemClickListener, View.OnClickListener {

    FB fb = new FB();
    private ImageView imageViewAccountPhoto;
    private Button buttonGetFriends, buttonAddActivity, buttonSeeQRcode, buttonScanQRcode;
    private TextView friendsTextView, suggestedFriendsTextView;


    private RecyclerView friendsRecyclerView, suggestedFriendsRecyclerView;
    private RecyclerViewAdapterSocial friendsAdapter, suggestedAdapter;
    private ArrayList<String> friendsUsernames = new ArrayList<>();
    private ArrayList<Integer> friendsUserPhotos = new ArrayList<>();
    private ArrayList<String> suggestedFriendsUsernames = new ArrayList<>();
    private ArrayList<Integer> suggestedFriendsUserPhotos = new ArrayList<>();


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

        friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        suggestedFriendsRecyclerView = findViewById(R.id.suggestedFriendsRecyclerView);
        initializeFriendsRecyclerView();
        initializeSuggestedFriendsRecyclerView();

        //suggestedFriendsRecyclerView = findViewById(R.id.suggestedFriendsRecyclerView);


        buttonGetFriends = findViewById(R.id.buttonGetFriends);//TODO: Delet
        buttonGetFriends.setOnClickListener(this);


        fb.getAllUsersFromDatabase();

        Log.d(Settings.getUsers().toString(), "onCreate: ");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                initializeFriends();
                initializeSuggestedFriends();
            }
        }, 500);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSeeQRcode:
                Intent seeQRcodeIntent = new Intent(this, SeeQRCodePopUpActivity.class);
                startActivity(seeQRcodeIntent);
                break;
            case R.id.buttonAddActivity:
                Intent addActivityIntent = new Intent(this, AddActivityPopUpActivity.class);
                startActivity(addActivityIntent);
                break;
            case R.id.buttonGetFriends:
                initializeFriends();
                initializeSuggestedFriends();

                for (int i = 0; i < 1000; i++) {
                    friendsUsernames.add("din far");
                    if(i % 2 == 0){
                        suggestedFriendsUsernames.add("din mor");
                    }
                }



                break;
            case R.id.buttonScanQRcode:
                Intent scanQRIntent = new Intent(this, ScanQRPopUpActivity.class);
                startActivity(scanQRIntent);
        }

    }

    public void initializeFriends() {
        for (User user : Settings.getUsers()) {
            if (user.getClass().equals(Pupil.class)
                    && !Settings.getCurrentPupil().getUID().equals(user.getUID())
                    && !Settings.getCurrentPupil().getFriends().contains(user.getUID())
                    && !friendsUsernames.contains(user.getUsername())) {
                friendsUsernames.add(user.getUsername());
                friendsUserPhotos.add(R.drawable.friend_nophoto);
                initializeFriendsRecyclerView();
            }
        }
    }

    public void initializeSuggestedFriends() {
        for (User user : Settings.getUsers()) {
            if (user.getClass().equals(Pupil.class)) {
                Pupil pupil = (Pupil) user;
                if ((Settings.getCurrentPupil().getPersonalInfo().getZipCode() == (pupil.getPersonalInfo().getZipCode())
                        || Settings.getCurrentPupil().getActivities().equals(pupil.getActivities()))
                        && !Settings.getCurrentPupil().getUID().equals(user.getUID())) {
                    suggestedFriendsUsernames.add(user.getUsername());
                    suggestedFriendsUserPhotos.add(R.drawable.friend_nophoto);
                    initializeSuggestedFriendsRecyclerView();
                }
            }
        }
    }

    public void initializeFriendsRecyclerView() {
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false));
        Log.d(friendsRecyclerView.getLayoutManager().toString(), "onCreate: ");
        friendsAdapter = new RecyclerViewAdapterSocial(this, friendsUsernames, friendsUserPhotos);
        friendsAdapter.setClickListener(this);
        friendsRecyclerView.setAdapter(friendsAdapter);
    }

    public void initializeSuggestedFriendsRecyclerView() {
        suggestedFriendsRecyclerView.setLayoutManager(new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false));
        // True reverselayout kunne være en mulighed
        Log.d(suggestedFriendsRecyclerView.getLayoutManager().toString(), "onCreate: ");
        suggestedAdapter = new RecyclerViewAdapterSocial(this, suggestedFriendsUsernames, suggestedFriendsUserPhotos);
        suggestedAdapter.setClickListener(this);
        suggestedFriendsRecyclerView.setAdapter(suggestedAdapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        // TODO: DIFFERENTIATE BETWEEN RECYCLERVIEWS=?!=!=!=!=!= HOW THE FK DO YOU DO THAT
        // TODO: denne her metode er fucked.
        // legede med getItem metode, fandt ud af det ikke virkede.
        Log.d(Integer.toString(view.getId()), "onItemClick: ");

        if ((int) view.getTag() == friendsUsernames.size()) {
            Toast.makeText(this, "You clicked " + friendsAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
        } else if ((int) view.getTag() == suggestedFriendsUsernames.size()) {
            Toast.makeText(this, "You clicked " + suggestedAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
        }


    }
  /*
        if (view.getId() == -1) {
            Toast.makeText(this, "You clicked " + friendsAdapter.getItem(view, position) + " on item position " + position, Toast.LENGTH_SHORT).show();
            //Log.d(view.getTag().toString(), "onItemClick: "); --- crashes nullpointer
        } else if (view.getId() == 0) {
            Toast.makeText(this, "You clicked " + suggestedAdapter.getItem(view, position) + " on item position " + position, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "DIDNT WORK", Toast.LENGTH_SHORT).show();
        }

    }


            case friendsRecyclerView.getLayoutManager().getItemViewType(view):
                Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
                break;
            case R.id.suggestedFriendsRecyclerView:

                break;

             */
}
