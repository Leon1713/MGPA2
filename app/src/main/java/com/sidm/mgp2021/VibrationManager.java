package com.sidm.mgp2021;
import android.graphics.Canvas;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.SurfaceView;
// start vibration
// stop vibration
// send command
public class VibrationManager {


    private Vibrator _vibrator;
    public final static VibrationManager instance = new VibrationManager();
    boolean isInIt = false;
    private VibrationManager()
    {}

    public void init(SurfaceView _view)
    {
        isInIt = true;
        _vibrator = (Vibrator) _view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
    }

    public boolean isinIt()
    {
        return isInIt;
    }

    public void startVibrate()
    {
        if(Build.VERSION.SDK_INT >= 26)
        {
            _vibrator.vibrate(VibrationEffect.createOneShot(150,10));
        }
        else
        {
            _vibrator.vibrate(VibrationEffect.EFFECT_CLICK);
        }
    }

    public void stopVibrate()
    {
        _vibrator.cancel();
    }


}
