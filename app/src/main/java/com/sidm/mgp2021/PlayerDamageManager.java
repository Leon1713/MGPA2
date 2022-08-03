package com.sidm.mgp2021;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceView;

public class PlayerDamageManager implements EntityBase,Collidable {
    private float xPos;
    private float yPos;
    private boolean collided = false;
    float Width;
    boolean isInit = false;
    float Height;
    private boolean isDone;
    private PlayerHealth playerHealth;
    SurfaceView view;
    @Override
    public String GetType() {
        return "damageManType";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return GetWidth()/2;
    }

    @Override
    public int GetWidth() {
        return (int)Width;
    }

    @Override
    public int GetHeight() {
        return (int)Height;
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType() == "EnemyType")
        {
            EnemyEntity temp = (EnemyEntity)_other;
            if(temp.IsDone() == false) {
                temp.SetIsDone(true);
                collided = true;
            }
        }
    }

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
        view = _view;
        xPos = 52 * view.getWidth()/50;
        yPos = 27 * view.getHeight()/50;
        Width = 10;
        Height = 10;
        playerHealth = PlayerHealth.instance;
    }
    @Override
    public void Update(float _dt) {
        if(collided)
        {
            collided = false;
            VibrationManager.instance.startVibrate();
            playerHealth.setHealth(playerHealth.getHealth() + 1); // incr is decr
        }
        else
        {
            Log.i("shea","Thea");
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        // Empty cause dn
        _canvas.drawRect((int)(xPos - Width/2),(int)(yPos - Height/2), (int)(xPos + Width/2), (int)(yPos + Height/2),new Paint());
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.GAMEOBJECTS_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_COLLIDER;
    }

    public static PlayerDamageManager Create()
    {
        PlayerDamageManager result = new PlayerDamageManager();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_COLLIDER);
        return result;
    }
}
