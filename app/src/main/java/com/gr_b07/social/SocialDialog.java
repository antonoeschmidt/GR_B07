package com.gr_b07.social;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.gr_b07.R;
import com.gr_b07.logik.Pupil;
import com.gr_b07.logik.Settings;
import com.gr_b07.nutrition.MealDialog;

public class SocialDialog extends AppCompatDialogFragment {
    private Pupil pupil;
    private DialogFragmentUpdateListener listener;
    private TextView friendNameTextView, friendUsernameTextView, friendActivityTextView;

    public SocialDialog(Pupil pupil) {
        this.pupil = pupil;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.social_dialog, null);
        builder.setView(view).setTitle("Ven info")
                .setNegativeButton("Annuller", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }
                ).setPositiveButton("Fjern ven", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Settings.getCurrentPupil().getFriends().remove(pupil.getUID());
                listener.updateFriends();
            }
        });

        friendNameTextView = view.findViewById(R.id.friendsNameTextView);
        friendUsernameTextView = view.findViewById(R.id.friendsUsernameTextView);
        friendActivityTextView = view.findViewById(R.id.friendsActivityTextView);

        friendNameTextView.setText(pupil.getPersonalInfo().getFirstName() + " " + pupil.getPersonalInfo().getLastName());
        Log.d((pupil.getPersonalInfo().getFirstName() + " " + pupil.getPersonalInfo().getLastName()), "onCreateDialog: firstlastname");
        friendUsernameTextView.setText(pupil.getUsername());
        //friendAgeTextView.setText(pupil.getPersonalInfo().getAge());
        friendActivityTextView.setText(pupil.getActivities().toString());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (DialogFragmentUpdateListener) context;
    }

    public interface DialogFragmentUpdateListener {
        void updateFriends();
    }

}
