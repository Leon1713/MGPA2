package com.sidm.mgp2021;

public class LeaderboardData {
    String Name;
    String Score;

    public LeaderboardData(String name, String score)
    {
        this.Name = name;
        this.Score = score;
    }

    public String getName() {
        return Name;
    }

    public String getScore() {
        return Score;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setScore(String score) {
        Score = score;
    }
}
