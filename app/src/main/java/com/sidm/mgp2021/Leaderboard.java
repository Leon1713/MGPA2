package com.sidm.mgp2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;
import java.util.Set;

public class Leaderboard extends Activity implements View.OnClickListener, StateBase {

    private RecyclerView recyclerView;
    private LeaderboardAdapter leaderboardAdapter;
    private Button btn_back_leaderboard;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.leaderboard);

        leaderboardAdapter = new LeaderboardAdapter(ScoreManager.instance.getData());
        btn_back_leaderboard = (Button)findViewById(R.id.btn_back_leaderboard);
        btn_back_leaderboard.setOnClickListener(this); //Set Listener to this button --> Back Button
        recyclerView = (RecyclerView) findViewById(R.id.eaderboardrecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(leaderboardAdapter);

        // write data to list
        Map<String,?> map = SaveManager.instance.getSharedPref().getAll();
        for(Map.Entry<String,?> entry : map.entrySet())
        {
            ScoreManager.instance.addData(entry.getKey(),Integer.toString((int)(entry.getValue())));
        }


    }

    @Override
    public void onClick(View v) {
        if(v == btn_back_leaderboard)
        {
            this.finish();
        }
    }

    @Override
    public String GetName() {
        return "Leaderboard";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public void Update(float _dt) {
       // leaderboardAdapter.setLeaderboardData(ScoreManager.instance.getData());
    }
}
