package com.sidm.mgp2021;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();
    public static final String SHARED_PREF_ID = "SaveGameFile";


    // Game stuff
    private boolean isPaused = false;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;

    // Singleton Pattern : Blocks others from creating

    private GameSystem()
    {
    }

    public void Update(float _deltaTime)
    {
    }

    public void Init(SurfaceView _view)
    {

        // Get SaveFile
        sharedPreferences = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID,0);

        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new Gameoverscreen());
        StateManager.Instance.AddState(new Leaderboard());
        StateManager.Instance.AddState(new Shop());
    }

    public void SetIsPaused(boolean _newIsPaused)
    {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused()
    {
        return isPaused;
    }

    public void SaveEditBegin()
    {
        if(editor != null)
            return;

        editor = sharedPreferences.edit();
    }
    public void SaveEditEnd()
    {
        if(editor == null)
            return;
        editor.commit();
        editor = null;
    }

    public void setIntInSave(String _key, int _value)
    {
        if(editor == null)
        {
            return;
        }
        editor.putInt(_key,_value);
    }
    public int  GetIntFromSave(String _key)
    {
        return sharedPreferences.getInt(_key,0);
    }

}
