package com.sidm.mgp2021;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

    TextView Name, Score;
    public LeaderboardViewHolder(@NonNull View itemView) {
        super(itemView);
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(),"fonts/digital-7.ttf");
        Name = (TextView) itemView.findViewById(R.id.name);
       // Name.setTextColor(56006);
        Name.setTypeface(typeface);
        Score = (TextView) itemView.findViewById(R.id.score);
        Score.setTypeface(typeface);
    }
}
