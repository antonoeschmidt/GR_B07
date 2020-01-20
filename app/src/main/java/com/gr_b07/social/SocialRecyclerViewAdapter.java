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

import java.util.List;

public class SocialRecyclerViewAdapter extends RecyclerView.Adapter<SocialRecyclerViewAdapter.ViewHolder> {
    private List<Integer> imageViewAccount;
    private List<String> username;
    private LayoutInflater inflater;
    private String name;

    // data is passed into the constructor
    SocialRecyclerViewAdapter(Context context, List<Integer> imageViewAccount, List<String> username) {
        this.inflater = LayoutInflater.from(context);
        this.imageViewAccount = imageViewAccount;
        this.username = username;
        this.name = name;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_social, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        int resource = imageViewAccount.get(position);
        String username = this.username.get(position);
        holder.accountPhotoTextView.setBackgroundResource(R.drawable.friend_nophoto);
        holder.usernameTextView.setText(username);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return username.size();
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
        return username.get(id);
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

        void onClick(View v);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
