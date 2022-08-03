package com.sidm.mgp2021;

// Created by TanSiewLan2020

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

public class RenderTextEntity implements EntityBase{

    // Paint object
    Paint paint = new Paint();
    private int red = 0, green = 0, blue = 0;


    private boolean isDone = false;
    private boolean isInit = false;

    int frameCount;
    long lastTime = 0;
    long lastFPSTime = 0;
    float fps = 0;
    int screenWidth, screenHeight;
    DisplayMetrics metrics;

    Typeface myfont;

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

        isInit = true;
       metrics = _view.getResources().getDisplayMetrics();
        // Week 8 Use my own fonts
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/digital-7.ttf");
        // myfont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        isInit = true;
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

    }

    @Override
    public void Update(float _dt) {

        if(GameSystem.Instance.GetIsPaused()) {
            return;
        }

        // get actual fps

        frameCount++;

        long currentTime = System.currentTimeMillis();

        lastTime = currentTime;

        if(currentTime - lastFPSTime > 1000)
        {
            fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }


    }

    @Override
    public void Render(Canvas _canvas)
    {


        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(GamePage.Instance,R.color.white));
        //paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);
        _canvas.drawText("FPS: " + (int)fps, 30, 80, paint);
        _canvas.drawText("Score: "+ ScoreManager.instance.getScore(),250,80,paint);
        if(EconsManager.instance != null)
        _canvas.drawText("Money: " + (int)EconsManager.instance.getMoney(), screenWidth - 330 ,screenHeight - 80,paint);
        else
            _canvas.drawText("Money: 0" ,30,160,paint);
        if(UnitSpawn.instance.notEnough)
        {
          //  _canvas.drawText("Not Enough Money",screenWidth * .5f - 80,80, paint);
        }

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_TEXT;}

    public static RenderTextEntity Create()
    {
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_TEXT);
        return result;
    }

}

