package com.sidm.mgp2021;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {
    private List<LeaderboardData> leaderboardData;

    public LeaderboardAdapter(List<LeaderboardData> leaderboardData)
    {
        this.leaderboardData = leaderboardData;
    }
    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboardcard,parent,false);
        LeaderboardViewHolder leaderboardViewHolder = new LeaderboardViewHolder(v);
        return  leaderboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        holder.Name.setText(leaderboardData.get(position).Name);
        holder.Score.setText(leaderboardData.get(position).Score);
    }

    public void setLeaderboardData(List<LeaderboardData> leaderboardData)
    {
        this.leaderboardData = leaderboardData;
    }
    @Override
    public int getItemCount() {
        return leaderboardData.size();
    }
}

