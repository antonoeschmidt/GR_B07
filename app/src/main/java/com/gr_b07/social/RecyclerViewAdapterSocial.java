package com.gr_b07.social;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gr_b07.R;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerViewAdapterSocial extends RecyclerView.Adapter<RecyclerViewAdapterSocial.ViewHolder> {

    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<Integer> userPhotos = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterSocial(ArrayList<String> usernames, ArrayList<Integer> userPhotos, Context context) {
        this.usernames = usernames;
        this.userPhotos = userPhotos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_social,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:called");
        holder.textViewFriend.setText(usernames.get(position));
        holder.imageViewFriend.setImageResource(userPhotos.get(position));

        holder.socialRecyclerParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, usernames.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usernames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFriend;
        ImageView imageViewFriend;
        RelativeLayout socialRecyclerParentView;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewFriend = itemView.findViewById(R.id.textViewFriend);
            imageViewFriend = itemView.findViewById(R.id.imageViewFriend);
            socialRecyclerParentView = itemView.findViewById(R.id.socialRecyclerParentView);
        }
    }

}
