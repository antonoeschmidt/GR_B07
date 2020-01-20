package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gr_b07.R;
import com.gr_b07.logik.FB;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.logik.User;

import java.util.ArrayList;

public class SocialActivity extends AppCompatActivity implements SocialRecyclerViewAdapter.ItemClickListener, View.OnClickListener {

    private TextView usersTextView, friendsTextView;
    private Button buttonIncrement, buttonAddActivity;
    private AutoCompleteTextView classmatesTextView;
    boolean firsttime = true;
    private ArrayList<Pupil> pupils = new ArrayList<Pupil>();
    FB fb = new FB();
    private SocialRecyclerViewAdapter friendsAdapter, suggestedAdapter;
    ArrayList<String> friendsUsername = new ArrayList<>();
    ArrayList<String> suggestedUsername = new ArrayList<>();
    ArrayList<Integer> friendsAccountImageView = new ArrayList<>();
    ArrayList<Integer> suggestedAccountImageView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        buttonIncrement = findViewById(R.id.buttonIncrement);
        buttonAddActivity = findViewById(R.id.buttonAddActivity);
        friendsTextView = findViewById(R.id.friendsTextView);
        buttonIncrement.setOnClickListener(this);



        // set up the RecyclerView


        classmatesTextView = findViewById(R.id.classmatesTextView);

        friendsRecyclerViewUpdate();

        Settings.getUsers().clear();

        fb.getAllUsersFromDatabase();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + friendsAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
        Log.d("onItemClick", "click");
        // TODO: Implement fragment for individual friend/suggested friend - options for friend : remove / invite / *close dialog* options for suggested : add / *close dialog*
        // side note : probably better to implement with UID from firebase instead of going through every user.
        //FragmentActivity
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
                    addFriends(user);
                    addSuggestedFriends(user);
                }
                friendsRecyclerViewUpdate();
                suggestedRecyclerViewUpdate();
                updateTextViews();

                System.out.println(Settings.getCurrentPupil().getFriends().toString());
                break;
        }
    }

    public void updateTextViews(){
        friendsTextView.setText("Friends : " + Integer.toString(Settings.getCurrentPupil().getFriends().size()));
    }

    public void friendsRecyclerViewUpdate(){

        RecyclerView friendsRecyclerView = findViewById(R.id.friendsRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        friendsRecyclerView.setLayoutManager(horizontalLayoutManager);
        friendsAdapter = new SocialRecyclerViewAdapter(this, friendsAccountImageView, friendsUsername);
        friendsRecyclerView.setAdapter(friendsAdapter);
    }
    public void suggestedRecyclerViewUpdate(){
        RecyclerView suggestedRecyclerView = findViewById(R.id.suggestedRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        suggestedRecyclerView.setLayoutManager(horizontalLayoutManager);
        suggestedAdapter = new SocialRecyclerViewAdapter(this, suggestedAccountImageView, suggestedUsername);
        suggestedRecyclerView.setAdapter(suggestedAdapter);
    }

    public void addSuggestedFriends(User user) {
        if (user.getClass().equals(Pupil.class)) {
            Pupil pupil = (Pupil) user;
            if ((Settings.getCurrentPupil().getPersonalInfo().getZipCode() == (pupil.getPersonalInfo().getZipCode())
                    || Settings.getCurrentPupil().getActivities().equals(pupil.getActivities()))
                    && Settings.getCurrentPupil().getUID() != user.getUID()
                    && !suggestedUsername.contains(user.getUsername())
                    && !suggestedUsername.contains(shortenUsername(user))) {
                suggestedAccountImageView.add(R.drawable.friend_nophoto);
                if (user.getUsername().length() > 12) {
                    suggestedUsername.add(shortenUsername(user));
                } else suggestedUsername.add(user.getUsername());
            }
        }
    }

    public void addFriends(User user) {
        if (user.getClass().equals(Pupil.class)
                && Settings.getCurrentPupil().getUID() != user.getUID()
                && !Settings.getCurrentPupil().getFriends().contains(user.getUID())
                && !friendsUsername.contains(user.getUsername())
                && !friendsUsername.contains(shortenUsername(user))) {
            friendsAccountImageView.add(R.drawable.friend_nophoto);
            Settings.getCurrentPupil().addFriend(user.getUID());
            if (user.getUsername().length() > 12) {
                friendsUsername.add(shortenUsername(user));
            } else friendsUsername.add(user.getUsername());

        }
    }

    public String shortenUsername(User user) {
        return user.getUsername().substring(0, 12) + "...";
    }

}
