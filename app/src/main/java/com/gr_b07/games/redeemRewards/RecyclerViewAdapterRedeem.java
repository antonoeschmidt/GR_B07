package com.gr_b07.games.redeemRewards; /**
 * RecyclerView inspired by intructional video by CodingWithMitch
 * https://www.youtube.com/watch?v=Vyqz_-sJGFk
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gr_b07.R;
import com.gr_b07.logik.Settings;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerViewAdapterRedeem extends RecyclerView.Adapter<RecyclerViewAdapterRedeem.ViewHolder> {

    private ArrayList<String> prizeNames = new ArrayList<>();
    private ArrayList<Integer> prizeImages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterRedeem(ArrayList<String> prizeNames, ArrayList<Integer> prizeImages, Context mContext) {
        this.prizeNames = prizeNames;
        this.prizeImages = prizeImages;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem_layout_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder:called");
        holder.prizeName.setText(prizeNames.get(position));
        holder.prizeImage.setImageResource(prizeImages.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext, prizeNames.get(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, RewardsPopUpActivity.class);
                intent.putExtra("rewardName", Settings.getCurrentPupil().getRewards().get(position).getName());
                intent.putExtra("rewardPhoto",Settings.getCurrentPupil().getRewards().get(position).getResource());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return prizeNames.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView prizeImage;
        TextView prizeName;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            prizeName = itemView.findViewById(R.id.word);
            parentLayout = itemView.findViewById(R.id.ParentLayout);
            prizeImage = itemView.findViewById(R.id.prizeImage);

        }
    }
}
