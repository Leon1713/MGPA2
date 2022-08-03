package com.sidm.mgp2021;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class SortByScore implements Comparator<LeaderboardData>
{


    @Override
    public  int compare(LeaderboardData o1, LeaderboardData o2) {
        return Integer.parseInt(o2.getScore()) - Integer.parseInt(o1.getScore());
    }
}
public class ScoreManager {
    public static final ScoreManager instance = new ScoreManager();
    private List<LeaderboardData> data = new ArrayList<>();
    String Name;
    int score;
    private ScoreManager() {
        data = new ArrayList<LeaderboardData>();
        score = 0;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void addData(String name, String Score)
    {
        LeaderboardData temp = new LeaderboardData(name,Score);
        for(int i = 0; i < data.size(); ++i)
        {
            if(data.get(i).Name == name){
                data.remove(i);
                data.add(i, new LeaderboardData(name,Score));
                Collections.sort(data, new SortByScore());
                return;
            }

        }
        data.add(temp);
        Collections.sort(data, new SortByScore());
    }

    public List<LeaderboardData> getData() {
        return data;
    }
}
