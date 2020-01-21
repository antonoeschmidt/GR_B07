package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity implements FriendsRecyclerViewAdapter.ItemClickListener, SuggestedFriendsRecyclerViewAdapter.ItemClickListener, View.OnClickListener {

    private TextView usersTextView, friendsTextView;
    private Button buttonIncrement;
    FB fb = new FB();
    private FriendsRecyclerViewAdapter friendsAdapter;
    private SuggestedFriendsRecyclerViewAdapter suggestedAdapter;
    ArrayList<String> friendsUsername = new ArrayList<>();
    ArrayList<String> suggestedUsername = new ArrayList<>();
    ArrayList<Integer> friendsAccountImageView = new ArrayList<>();
    ArrayList<Integer> suggestedAccountImageView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        friendsTextView = findViewById(R.id.friendsTextView);
        buttonIncrement.setOnClickListener(this);
        friendsRecyclerViewUpdate();
        Settings.getUsers().clear();
        fb.getAllUsersFromDatabase();
    }

    @Override
    public void onItemClick(View view, int position) {

        Toast.makeText(this, "You clicked " + friendsAdapter.getItemId(position) + " on item position " + position, Toast.LENGTH_SHORT).show();

        // TODO: Implement fragment for individual friend/suggested friend - options for friend : remove / invite / *close dialog* options for suggested : add / *close dialog*
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonIncrement:
                for (User user : Settings.getUsers()) {
                    addFriendsToRecyclerView(user);
                    addSuggestedFriendsToRecyclerView(user);
                }
                friendsRecyclerViewUpdate();
                suggestedRecyclerViewUpdate();
                updateTextViews();
                break;
        }
    }

    public void updateTextViews() {
        friendsTextView.setText("Friends : " + Integer.toString(Settings.getCurrentPupil().getFriends().size()));
    }

    public void friendsRecyclerViewUpdate() {
        RecyclerView friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        friendsRecyclerView.setLayoutManager(horizontalLayoutManager);
        friendsAdapter = new FriendsRecyclerViewAdapter(friendsUsername, friendsAccountImageView, this);
        friendsAdapter.setClickListener(this);
        friendsRecyclerView.setAdapter(friendsAdapter);
    }

    public void suggestedRecyclerViewUpdate() {
        RecyclerView suggestedRecyclerView = findViewById(R.id.suggestedRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        suggestedRecyclerView.setLayoutManager(horizontalLayoutManager);
        suggestedAdapter = new SuggestedFriendsRecyclerViewAdapter(this, suggestedAccountImageView, suggestedUsername);
        suggestedAdapter.setClickListener(this);
        suggestedRecyclerView.setAdapter(suggestedAdapter);
    }

    public void addSuggestedFriendsToRecyclerView(User user) {
        if (user.getClass().equals(Pupil.class)) {
            Pupil pupil = (Pupil) user;
            if ((Settings.getCurrentPupil().getPersonalInfo().getZipCode() == (pupil.getPersonalInfo().getZipCode())
                    || Settings.getCurrentPupil().getActivities().equals(pupil.getActivities()))
                    && !Settings.getCurrentPupil().getUID().equals(user.getUID())
                    && !suggestedUsername.contains(user.getUsername())) {
                suggestedAccountImageView.add(R.drawable.friend_nophoto);
                suggestedUsername.add(user.getUsername());
            }
        }
    }

    public void addFriendsToRecyclerView(User user) {
        if (user.getClass().equals(Pupil.class)
                && !Settings.getCurrentPupil().getUID().equals(user.getUID())
                && !Settings.getCurrentPupil().getFriends().contains(user.getUID())
                && !friendsUsername.contains(user.getUsername())) {
            friendsAccountImageView.add(R.drawable.friend_nophoto);
            Settings.getCurrentPupil().addFriend(user.getUID());
            friendsUsername.add(user.getUsername());

        }
    }
}
