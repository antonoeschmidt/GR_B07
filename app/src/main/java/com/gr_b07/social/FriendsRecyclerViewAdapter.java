package com.gr_b07.social;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gr_b07.R;

import java.util.ArrayList;

public class FriendsRecyclerViewAdapter extends RecyclerView.Adapter<FriendsRecyclerViewAdapter.ViewHolder> { //heasjkdfsjakfgsjlkadjksladjsaklddsa

    private ArrayList<String> usernames;
    private ArrayList<Integer> accountImages;
    private Context context;

    // data is passed into the constructor
    FriendsRecyclerViewAdapter(ArrayList<String> usernames, ArrayList<Integer> accountImages, Context context) {
        this.usernames = usernames;
        this.accountImages = accountImages;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_social, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.usernameTextView.setText(usernames.get(position));
        if (accountImages.get(position) == null){
            holder.accountPhotoTextView.setBackgroundResource(R.drawable.friend_nophoto);
        } else{

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement this
                Toast.makeText(context, "You clicked" + position + " on " , Toast.LENGTH_SHORT).show();
                //Toast.makeText(mContext, mealnames.get(position),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(mContext, rewardsPopUpActivity.class);
                //mContext.startActivity(intent);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return usernames.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View accountPhotoTextView;
        TextView usernameTextView;

        ViewHolder(View itemView) {
            super(itemView);
            accountPhotoTextView = itemView.findViewById(R.id.imageViewAccount);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return usernames.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
       // this.itemClickListener = itemClickListener;
    }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onClick(View v);

            void onItemClick(View view, int position);
        }
}
