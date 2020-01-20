package com.gr_b07.social;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gr_b07.R;

import java.util.List;

public class FriendsRecyclerViewAdapter extends RecyclerView.Adapter<FriendsRecyclerViewAdapter.ViewHolder> {
    private List<Integer> imageViewAccount;
    private List<String> username;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    // data is passed into the constructor
    FriendsRecyclerViewAdapter(Context context, List<Integer> imageViewAccount, List<String> username) {
        this.inflater = LayoutInflater.from(context);
        this.imageViewAccount = imageViewAccount;
        this.username = username;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = imageViewAccount.get(position);
        String username = this.username.get(position);
        holder.accountPhotoTextView.setBackgroundResource(resource);
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
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return username.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onClick(View v);

            void onItemClick(View view, int position);
        }
}
