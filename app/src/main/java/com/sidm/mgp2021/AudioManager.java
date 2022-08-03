package com.sidm.mgp2021;

import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.SurfaceView;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    public final static AudioManager instance = new AudioManager();
    private SurfaceView view = null;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();
    private AudioManager(){
    }
    public void Init(SurfaceView _view)
    {
        this.view = _view;
        Release(); // Clear the audioMap
    }

    public void PlayAudio(int _id, float _vol)
    {
        //if audio is loaded
        if(audioMap.containsKey(_id))
        {
            // have the Clip
            MediaPlayer curr = audioMap.get(_id);
            curr.seekTo(0);
            curr.setVolume(_vol, _vol);
            curr.start();
        }
        else
        {
            MediaPlayer curr = MediaPlayer.create(view.getContext(), _id);
            audioMap.put(_id, curr);
            curr.start();
        }
    }

    public void StopAudio(int _id)
    {
        MediaPlayer curr = audioMap.get(_id);
        curr.stop();
    }

    public void Release()
    {
        for(HashMap.Entry<Integer, MediaPlayer> entry : audioMap.entrySet())
        {
            entry.getValue().stop();
            entry.getValue().reset();
            entry.getValue().release();
        }
        audioMap.clear();
    }

    private MediaPlayer GetAudio(int _id)
    {
        // Check if audio is loaded or not
        if(audioMap.containsKey(_id))
        {
            // Has the clip and return it
            return audioMap.get(_id);
        }
        // Load it if not
        MediaPlayer result = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id,result);
        return result;
    }
}
