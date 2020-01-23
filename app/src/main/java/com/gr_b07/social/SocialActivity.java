package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;
import com.gr_b07.nutrition.MealDialog;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity implements RecyclerViewAdapterSocial.ItemClickListener, View.OnClickListener, SocialDialog.DialogFragmentUpdateListener {

    private FB fb = new FB();
    private ImageView imageViewAccountPhoto;
    private Button buttonGetFriends, buttonAddActivity, buttonSeeQRcode, buttonScanQRcode;
    private TextView friendsTextView, suggestedFriendsTextView;


    private RecyclerView friendsRecyclerView, suggestedFriendsRecyclerView;
    private RecyclerViewAdapterSocial friendsAdapter, suggestedAdapter;
    private ArrayList<String> friendsUsernames = new ArrayList<>();
    private ArrayList<Integer> friendsUserPhotos = new ArrayList<>();
    private ArrayList<String> suggestedFriendsUsernames = new ArrayList<>();
    private ArrayList<Integer> suggestedFriendsUserPhotos = new ArrayList<>();
    private String resultFromScan = "";


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

        buttonGetFriends = findViewById(R.id.buttonGetFriends);
        buttonGetFriends.setOnClickListener(this);
        buttonGetFriends.setVisibility(View.INVISIBLE);


        fb.getAllUsersFromDatabase();

        Log.d(Settings.getUsers().toString(), "onCreate: ");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                initializeFriends();
                initializeSuggestedFriends();
                checkArraySizes();

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
                for (int i = 0; i < 1000; i++) {
                    friendsUsernames.add("din far");
                    if (i % 2 == 0) {
                        suggestedFriendsUsernames.add("din mor");
                    }
                }
                initializeFriends();
                initializeSuggestedFriends();
                checkArraySizes();


                break;
            case R.id.buttonScanQRcode:
                scan(this);
        }

    }

    public void initializeFriends() {
        friendsUsernames.clear();
        for (String friend : Settings.getCurrentPupil().getFriends()) {
            Pupil pupil = (Pupil) getFriendFromUID(friend);
            if (friendsUsernames.contains(pupil.getUID())) {
            } else if (!friendsUsernames.contains(friend)) {
                friendsUsernames.add(pupil.getUID());
                friendsUserPhotos.add(R.drawable.friend_nophoto);
                initializeFriendsRecyclerView();
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

    public void initializeSuggestedFriendsRecyclerView() {
        suggestedFriendsRecyclerView.setLayoutManager(new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false));
        Log.d(suggestedFriendsRecyclerView.getLayoutManager().toString(), "onCreate: ");
        suggestedAdapter = new RecyclerViewAdapterSocial(this, suggestedFriendsUsernames, suggestedFriendsUserPhotos);
        suggestedAdapter.setClickListener(this);
        suggestedFriendsRecyclerView.setAdapter(suggestedAdapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.d(Integer.toString(view.getId()), "onItemClick: ");
        if ((int) view.getTag() == friendsUsernames.size()) {
            Toast.makeText(this, "You clicked " + friendsAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
            openDialog(position);
        } else if ((int) view.getTag() == suggestedFriendsUsernames.size()) {
            Toast.makeText(this, "You clicked " + suggestedAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
        }


    }

    private void openDialog(int position) {
        SocialDialog socialDialog = new SocialDialog((Pupil) getFriendFromUID(friendsUsernames.get(position)));
        FragmentManager manager = (getSupportFragmentManager());
        socialDialog.show(manager,"meal dialog");
    }

    public void checkArraySizes() {
        if (friendsUsernames.size() == suggestedFriendsUsernames.size() && friendsUsernames.size() != 0) {
            suggestedFriendsUsernames.remove(suggestedFriendsUsernames.size() - 1);
            suggestedFriendsUserPhotos.remove(suggestedFriendsUserPhotos.size() - 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {

            } else {
                resultFromScan = result.getContents();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        returnFromScan();
    }

    public void scan(Activity activty) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    public void returnFromScan() {
        if (resultFromScan.isEmpty()) {
            Toast.makeText(this, "Du annulerede scanningen", Toast.LENGTH_SHORT).show();
        } else if (!resultFromScan.isEmpty()) {
            if (resultFromScan.length() == 28) {
                if (Settings.getCurrentPupil().getFriends().contains(resultFromScan)) {
                    Toast.makeText(this, "I er allerede venner.", Toast.LENGTH_SHORT).show();
                } else if (!Settings.getCurrentPupil().getFriends().contains(resultFromScan)) {
                    Settings.getCurrentPupil().getFriends().add(resultFromScan);
                    Toast.makeText(this, "Du har tilføjet \n" + resultFromScan, Toast.LENGTH_SHORT).show();
                    Settings.getCurrentPupil().getExperience().setSocialXP(Settings.getCurrentPupil().getExperience().getSocialXP() + 5);
                    fb.updateDatabase();
                }
            }
            initializeFriends();
        }
    }

    public User getFriendFromUID(String UID){
        for (User user : Settings.getUsers()) {
            if (user.getClass().equals(Pupil.class)){
                if (user.getUID().equals(UID)){
                    return user;
                }
            }
        }
        return null;

    }

    @Override
    public void updateFriends() {
        initializeFriends();
        friendsAdapter.notifyDataSetChanged();
        fb.updateDatabase();
    }
}
