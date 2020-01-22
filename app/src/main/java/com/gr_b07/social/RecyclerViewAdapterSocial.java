package com.gr_b07.social;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gr_b07.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerViewAdapterSocial extends RecyclerView.Adapter<RecyclerViewAdapterSocial.ViewHolder> {

    private List<String> usernames = new ArrayList<>();
    private List<Integer> userPhotos = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;


    public RecyclerViewAdapterSocial(Context context, List<String> usernames, List<Integer> userPhotos) {
        this.layoutInflater = LayoutInflater.from(context);
        this.usernames = usernames;
        this.userPhotos = userPhotos;

    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyclerview_social,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String username = usernames.get(position);
        int photo = R.drawable.friend_nophoto;
        holder.textViewFriend.setText(username);
        holder.imageViewFriend.setImageResource(photo);
    }

    @Override
    public int getItemCount() {
        return usernames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewFriend;
        ImageView imageViewFriend;
        ViewHolder(View itemView) {
            super(itemView);
            textViewFriend = itemView.findViewById(R.id.textViewFriend);
            imageViewFriend = itemView.findViewById(R.id.imageViewFriend);
            itemView.setOnClickListener(this);
            itemView.setTag((int) usernames.size()); // TODO: SLET HVIS IKKE VIRKER
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    public String getItem( int id) {
            return usernames.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
