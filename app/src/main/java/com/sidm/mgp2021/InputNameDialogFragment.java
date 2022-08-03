package com.sidm.mgp2021;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

public class InputNameDialogFragment extends DialogFragment {
    public static boolean isShown = false;

    public Dialog onCreateDialog(Bundle saveInstanceState)
    {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter name");
        final EditText input = new EditText(getActivity());
        builder.setView(input);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String temp = input.getText().toString();
                        ScoreManager.instance.setName(temp);
                        SaveManager.instance.SaveEditBegin();
                        SaveManager.instance.SetIntInSave(temp,ScoreManager.instance.getScore());
                        SaveManager.instance.SaveEditEnd();
                        ScoreManager.instance.setScore(0);
                        ScoreManager.instance.setName(null);
                        isShown = false;
                       // do nth
                    }
                });
        return builder.create();
    }
}
