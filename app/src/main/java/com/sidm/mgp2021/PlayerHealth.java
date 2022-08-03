package com.sidm.mgp2021;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.view.SurfaceView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.helper.widget.Layer;

public class PlayerHealth implements EntityBase{
    SurfaceView surfaceView;
    int renderLayer;
    boolean isInit = false;
    boolean isDone = false;
    boolean tookDamaged;
    float xPos, yPos;
    int health;
    int max_health;
    public boolean isGameOver;
    float margin = 2.0f;
    Bitmap healthInner;
    Bitmap healthBackGround;
    Bitmap healthBorder;
    Intent intent;
    Sprite healthIcon;
    public static PlayerHealth instance;
    private StateManager stateManager;
    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

        isGameOver = false;
        instance = this;

        if(StateManager.Instance != null)
        stateManager = StateManager.Instance;

        tookDamaged = false;
        healthIcon = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.heart_animated_2),1,5,8 );
        healthBackGround = ResourceManager.Instance.GetBitmap(R.drawable.background);
        healthInner = ResourceManager.Instance.GetBitmap(R.drawable.heart);
        healthBorder = ResourceManager.Instance.GetBitmap(R.drawable.border);
        max_health = 4;
        health = 0;
        surfaceView = _view;
        xPos = 174;
        yPos = 204;
        isInit = true;
       healthIcon.Scale(2,2);
       intent = new Intent();

    }

    public boolean isTookDamaged() {
        return tookDamaged;
    }

    public void setTookDamaged(boolean tookDamaged) {
        this.tookDamaged = tookDamaged;
    }

    @Override
    public void Update(float _dt) {
        healthIcon.SetAnimationFrames(health  ,health);
        if(health >= max_health) {
            health = 4;
            isGameOver = true;
            stateManager.ChangeState("Gameoverscreen");
            intent.setClass(GamePage.Instance,Gameoverscreen.class);
            GamePage.Instance.startActivity(intent);
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public  void Scale(int x, int y)
    {
        healthBackGround = Bitmap.createScaledBitmap(healthBackGround,(int)(healthBackGround.getWidth() * x),(int)(healthBackGround.getHeight() * y),false);
        healthInner = Bitmap.createScaledBitmap(healthInner,(int)(healthInner.getWidth() * x),(int)(healthInner.getHeight() * y),false);
        healthBorder = Bitmap.createScaledBitmap(healthBorder,(int)(healthBorder.getWidth() * x),(int)(healthBorder.getHeight() * y),false);

    }

    @Override
    public void Render(Canvas _canvas) {
//        Rect dstBorder = new Rect((int)(xPos - healthBorder.getWidth()/2),(int)(yPos - healthBorder.getHeight()/2),(int)(xPos +healthBorder.getWidth()/2),(int)(yPos + healthBorder.getHeight()/2));
//        Rect Src = new Rect((int)(xPos - healthBorder.getWidth()/2),(int)(yPos - healthBorder.getHeight()/2),(int)(xPos +healthBorder.getWidth()/2),(int)(yPos + healthBorder.getHeight()/2));
//        Rect dstBackground = new Rect((int)(xPos - healthBackGround.getWidth()/2),(int)(yPos - healthBackGround.getHeight()/2),(int)(xPos +healthBackGround.getWidth()/2),(int)(yPos + healthBackGround.getHeight()/2));
//        Rect dstInner = new Rect((int)(xPos - healthInner.getWidth()/2),(int)(yPos - healthInner.getHeight()/2),(int)(xPos +healthInner.getWidth()/2),(int)(yPos + healthInner.getHeight()/2));
//
//        _canvas.drawBitmap(healthBorder,null,dstBorder,null);
//        _canvas.drawBitmap(healthBackGround,null,dstBackground,null);
//        _canvas.drawBitmap(healthInner,null,dstInner,null);
        healthIcon.Render(_canvas,(int)xPos,(int)yPos);


    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.UI_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        renderLayer = _newLayer;
    }

    @Override
    public ENTITY_TYPE GetEntityType() {

        return ENTITY_TYPE.ENT_HEALTHBAR;
    }
    public static PlayerHealth Create() {
        PlayerHealth result = new PlayerHealth(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_HEALTHBAR); //wk8=>update ent type
        return result;
    }
}
