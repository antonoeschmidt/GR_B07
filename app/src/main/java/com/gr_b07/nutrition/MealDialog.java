package com.gr_b07.nutrition;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.gr_b07.R;
import com.gr_b07.logik.Meal;
import com.gr_b07.logik.Settings;

/** Inspiration taget fra https://www.youtube.com/watch?v=ARezg1D9Zd0&t=22s **/

public class MealDialog extends AppCompatDialogFragment {
    private TextView caloriesTextView, proteinTextView, carbsTextView, fatTextView, dateTextView;
    private Meal meal;
    private DialogFragmentUpdateListener listener;

    public MealDialog(Meal meal) {
        this.meal = meal;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.meal_dialog, null);
        builder.setView(view).setTitle(meal.getTypeOfMeal())
                .setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Fjern måltid", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(Settings.getCurrentPupil().getMeals().toString());
                        Settings.getCurrentPupil().getMeals().remove(meal);
                        System.out.println(Settings.getCurrentPupil().getMeals().toString());
                        listener.updateMeals();
                        Toast.makeText(getContext(), "Måltid fjernet", Toast.LENGTH_SHORT).show();

                    }
                });

        caloriesTextView = view.findViewById(R.id.caloriesTextView);
        proteinTextView = view.findViewById(R.id.proteinTextView);
        carbsTextView = view.findViewById(R.id.carbsTextView);
        fatTextView = view.findViewById(R.id.fatTextView);
        dateTextView = view.findViewById(R.id.dateTextView);

        caloriesTextView.setText(meal.getCalories() + " kalorier");
        proteinTextView.setText(meal.getProtein() + " gram protein");
        carbsTextView.setText(meal.getCarbs() + " gram kulhydrater");
        fatTextView.setText(meal.getFat() + " gram fedt");
        dateTextView.setText("Spist d. " + Settings.longToStringDate(meal.getDate()));

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
