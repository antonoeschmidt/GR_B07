package com.gr_b07.nutrition;

import android.app.Activity;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gr_b07.R;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;

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
        holder.mealname.setText(meals.get(position).getName());

        //holder.mealImage.setImageResource(mealImages.get(position));
        //TODO: put new images
        if (meals.get(position).getTypeOfMeal().equals("Breakfast")) {
            holder.mealImage.setImageResource(R.drawable.ic_breakfast);

        } else if (meals.get(position).getTypeOfMeal().equals("Lunch")) {
            holder.mealImage.setImageResource(R.drawable.ic_sandwich);
        } else if (meals.get(position).getTypeOfMeal().equals("Dinner")) {
            holder.mealImage.setImageResource(R.drawable.ic_local_dining_black_24dp);
        } else {
            holder.mealImage.setImageResource(R.drawable.ic_fruit);
        }

        holder.parentLayoutMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(position);
            }
        });
    }

    private void openDialog(int position) {
        Meal meal = Settings.getCurrentPupil().getMeals().get(position);
        MealDialog mealDialog = new MealDialog(meal);
        FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
        mealDialog.show(manager,"meal dialog");
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


