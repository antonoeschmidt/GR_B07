package com.gr_b07.nutrition;

import android.content.Context;
import android.content.Intent;
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
import com.gr_b07.logik.Meal;

import java.util.ArrayList;

public class RecyclerViewAdapterMeals extends RecyclerView.Adapter<RecyclerViewAdapterMeals.ViewHolder> {

    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<Integer> mealImages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterMeals(ArrayList<Meal> meals, ArrayList<Integer> mealImages, Context mContext) {
        this.meals = meals;
        this.mealImages = mealImages;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem_layout_list_item_meals, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("hej", "onBindViewHolder:called");
        holder.mealname.setText(meals.get(position).getName());

        //holder.mealImage.setImageResource(mealImages.get(position));
        //TODO: put new images
        if (meals.get(position).getTypeOfMeal().equals("Breakfast")) {
            holder.mealImage.setImageResource(R.drawable.apple);

        } else if (meals.get(position).getTypeOfMeal().equals("Lunch")) {
            holder.mealImage.setImageResource(R.drawable.aubergine);
        } else if (meals.get(position).getTypeOfMeal().equals("Dinner")) {
            holder.mealImage.setImageResource(R.drawable.carrot);
        } else {
            holder.mealImage.setImageResource(R.drawable.common_google_signin_btn_text_dark_focused);
        }

        holder.parentLayoutMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement this
                //Toast.makeText(mContext, mealnames.get(position),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(mContext, rewardsPopUpActivity.class);
                //mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImage;
        TextView mealname;
        RelativeLayout parentLayoutMeals;

        public ViewHolder(View itemView) {
            super(itemView);
            mealname = itemView.findViewById(R.id.mealName);
            parentLayoutMeals = itemView.findViewById(R.id.ParentLayoutMeals);
            mealImage = itemView.findViewById(R.id.mealImage);

        }
    }
}


