package com.sidm.mgp2021;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class PauseConfirmDialogFragment extends DialogFragment {
    public static boolean isShown = false;

    public Dialog onCreateDialog(Bundle saveInstanceState)
    {

        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameSystem.Instance.SetIsPaused(true);
                        isShown =false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameSystem.Instance.SetIsPaused(false);
                        isShown = false;
                    }
                });
        return builder.create();
    }
}
