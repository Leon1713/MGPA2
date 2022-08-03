package com.sidm.mgp2021;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

public class SaveManager {
    public static final SaveManager instance = new SaveManager();
    private SharedPreferences  sharedPref = null;
    private SharedPreferences.Editor editor = null;

    public void Init(SurfaceView _view)
    {
        sharedPref = GamePage.Instance.getSharedPreferences(GameSystem.SHARED_PREF_ID,0);
    }

    public SharedPreferences getSharedPref() {
        return sharedPref;
    }

    public void setSharedPref(Context context)
    {
        sharedPref = context.getSharedPreferences("SaveGameFile", 0);
    }

    public void SaveEditBegin()
    {
        if(editor != null)
            return;
        editor = sharedPref.edit();
    }

    public void SaveEditEnd()
    {
        if(editor == null)
            return;
        editor.commit();
        editor = null;
    }

    public void SetIntInSave(String _key,int _val)
    {
        if(editor == null)
            return;
        editor.putInt(_key, _val);
    }

    public int GetIntFromSave(String _key)
    {
        return sharedPref.getInt(_key,0);
    }

}
