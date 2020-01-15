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
    private SocialRecyclerViewAdapter adapter;
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<Integer> imageViewAccount = new ArrayList<>();

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

        recyclerViewUpdate();

        Settings.getUsers().clear();

        fb.getAllUsersFromDatabase();

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
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
                        usernames.add(user.getUsername());
                        if (((Pupil) user).getResource() == null){
                            imageViewAccount.add(R.drawable.friend_nophoto);
                        } else if (((Pupil) user).getResource() != null){
                            imageViewAccount.add(Color.BLUE);
                            // TODO: Save resource and get it out again lol
                            //imageViewAccount.add(Resources.(((Pupil) user).getResource()));
                            //imageViewAccount.add(((Pupil) user).getResource());
                        }
                        recyclerViewUpdate();
                    }
                updateTextViews();
                break;
        }
    }
    public void updateTextViews(){
        friendsTextView.setText("Friends : " + Integer.toString(Settings.getCurrentPupil().getFriends().size()));
    }

    public void recyclerViewUpdate(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFriends);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(SocialActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new SocialRecyclerViewAdapter(this, imageViewAccount, usernames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}
