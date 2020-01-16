package com.gr_b07.social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
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

        for (User user : Settings.getUsers())
            if (user.getClass() == Pupil.class){
                Pupil pupil = (Pupil) user;
                if (Settings.getCurrentPupil().getZipCode().equals(pupil.getZipCode())|| Settings.getCurrentPupil().getActivities().equals(pupil.getActivities())){
                suggestedAccountImageView.add(R.drawable.friend_nophoto);
                suggestedUsername.add(pupil.getUsername());

                }
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + friendsAdapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonIncrement:
                for (User user : Settings.getUsers())
                    if (user.getClass().equals(Pupil.class) && !user.getUsername().equals(Settings.getCurrentPupil().getUsername())){
                        Settings.getCurrentPupil().addFriend((Pupil) user);
                        friendsUsername.add(user.getUsername());
                        if (((Pupil) user).getResource() == null){
                            friendsAccountImageView.add(R.drawable.friend_nophoto);
                        } else if (((Pupil) user).getResource() != null){
                            friendsAccountImageView.add(Color.BLUE);
                            // TODO: Save resource and get it out again lol
                            //friendsAccountImageView.add(Resources.(((Pupil) user).getResource()));
                            //friendsAccountImageView.add(((Pupil) user).getResource());
                        }
                        friendsRecyclerViewUpdate();

                        suggestedRecyclerViewUpdate();
                    }
                updateTextViews();
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
        friendsAdapter.setClickListener(this);
        friendsRecyclerView.setAdapter(friendsAdapter);
    }
    public void suggestedRecyclerViewUpdate(){
        RecyclerView suggestedRecyclerView = findViewById(R.id.suggestedRecyclerView);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        suggestedRecyclerView.setLayoutManager(horizontalLayoutManager);
        suggestedAdapter = new SocialRecyclerViewAdapter(this, suggestedAccountImageView, suggestedUsername);
        suggestedAdapter.setClickListener(this);
        suggestedRecyclerView.setAdapter(suggestedAdapter);
    }
}
