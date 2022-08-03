package com.sidm.mgp2021;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.Button;

public class PauseRenderEntity implements EntityBase {


    public Bitmap buttonBackToMainUp, buttonBackToMainDown; // button for resume amd exit to main

    private float xPosunPause = 0, yPosunPause = 0 , xPosExit = 0, yPosExit = 0;

    private boolean isPressed = false;

    float ElaspedTime = 1.f;
    private boolean isPressedExit = false;
    private boolean isPressedResume = false;
    private boolean isDone = false;
    private boolean isInit = false;
    private boolean Paused = false;

    Intent intent;
    int ScreenWidth, ScreenHeight;
    float  exitWidth;


    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

     // 1, find image
     // 2 & 3 when pause then update
     // 2. check collision
     // 3. Render
        intent = new Intent();

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();

        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;


        buttonBackToMainDown = ResourceManager.Instance.GetBitmap(R.drawable.backbuttondown);
        buttonBackToMainUp = ResourceManager.Instance.GetBitmap(R.drawable.backbutton);


        xPosExit = ScreenWidth/2;
        yPosExit = ScreenHeight/2;

        exitWidth =  ScreenWidth/12;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(!GameSystem.Instance.GetIsPaused())
        {
            return;
        }
        if(TouchManager.Instance.HasTouch())
        {
            if(TouchManager.Instance.IsDown())
            {
                // check collision for resume
                if(Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPosExit,yPosExit,exitWidth/2))
                {
                    // set backTomainMenu
                    isPressed = true;
                    isPressedExit = true;
                    //StateManager.Instance.ChangeState("Mainmenu");
                    ElaspedTime = 0.f;
                }
            }
        }
        else
        {
            isPressed = false;
        }

        ElaspedTime += _dt;
        // check collision with touch and button if resume is touched then resume game
        //else if exit is touched then statemanager.changestate == "Mainmenu";
    }

    @Override
    public void Render(Canvas _canvas) {
        if(GameSystem.Instance.GetIsPaused() && !TutorialManager.instance.isTutorial)
        {
            if(isPressed)
            {
                Bitmap temp = Bitmap.createScaledBitmap(buttonBackToMainDown, (int) (ScreenWidth)/12, (int)(ScreenWidth)/12, true);
                _canvas.drawBitmap(temp,xPosExit - temp.getWidth()/2,yPosExit - temp.getHeight()/2,null);
            }
            else
            {
                Bitmap temp = Bitmap.createScaledBitmap(buttonBackToMainUp, (int) (ScreenWidth)/12, (int)(ScreenWidth)/12, true);
                _canvas.drawBitmap(temp,xPosExit - temp.getWidth()/2,yPosExit - temp.getHeight()/2,null);
                if(isPressedExit)
                {
                    StateManager.Instance.ChangeState("Mainmenu");
                    GamePage.Instance.finish();
                    //GamePage.Instance.startActivity(intent);
                }
            }
        }
    }



    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PAUSEBUTTON_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_PAUSE;}

    public static PauseRenderEntity Create()
    {
        PauseRenderEntity result = new PauseRenderEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }

}
