package com.gr_b07.nutrition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.gr_b07.R;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;

public class MealDialog extends AppCompatDialogFragment {
    private TextView caloriesTextView, proteinTextView, carbsTextView, fatTextView, dateTextView;
    private Meal meal;
    private RecyclerViewAdapterMeals recyclerViewAdapterMeals;
    private DialogFragmentUpdateListener listener;

    public MealDialog(Meal meal, RecyclerViewAdapterMeals recyclerViewAdapterMeals) {
        this.meal = meal;
        this.recyclerViewAdapterMeals = recyclerViewAdapterMeals;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Intent intent = getActivity().getIntent();
        //Meal meal = (Meal) intent.getSerializableExtra("meal");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.meal_dialog, null);
        builder.setView(view).setTitle("Meal")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(Settings.getCurrentPupil().getMeals().toString());
                        Settings.getCurrentPupil().getMeals().remove(meal);
                        System.out.println(Settings.getCurrentPupil().getMeals().toString());
                        //recyclerViewAdapterMeals.notifyDataSetChanged();
                        listener.updateMeals();

                    }
                });

        caloriesTextView = view.findViewById(R.id.caloriesTextView);
        proteinTextView = view.findViewById(R.id.proteinTextView);
        carbsTextView = view.findViewById(R.id.carbsTextView);
        fatTextView = view.findViewById(R.id.fatTextView);
        dateTextView = view.findViewById(R.id.dateTextView);

        caloriesTextView.setText("" + meal.getCalories());
        proteinTextView.setText(" ");
        carbsTextView.setText(" ");
        fatTextView.setText(" ");
        dateTextView.setText(" ");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogFragmentUpdateListener) context;
    }

    public interface DialogFragmentUpdateListener {
        void updateMeals();
    }
}
