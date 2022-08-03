package com.sidm.mgp2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Button;

public class UnitSpawn implements EntityBase{

    public static UnitSpawn instance;
    private TouchManager touchManager;
    private Spawner spawner;
    Bitmap button = ResourceManager.Instance.GetBitmap(R.drawable.settingicon);
    float xPos;
    float yPos;
    boolean isInit = false;
    boolean unitSpawned = false;
    private int ScreenWidth, ScreenHeight;
    EconsManager econsManager;
    EnemySpawnManager enemySpawnManager;
    boolean notEnough;

    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    @Override
    public void Init(SurfaceView _view) {
        enemySpawnManager = new EnemySpawnManager(0);
        econsManager = new EconsManager();
        econsManager.Init();
        isInit = true;
        instance = this;
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        spawner = EntityManager.spawner;
        touchManager = TouchManager.Instance;
        xPos = 150;
        yPos = ScreenHeight - 150;

    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) {
            return;
        }
        enemySpawnManager.Update(_dt);
        if(EconsManager.instance.getMoney() >= 100)
        {
            UnitSpawn.instance.notEnough = false;
        }
        else
        {
            UnitSpawn.instance.notEnough = true;
        }
        if(touchManager.HasTouch())
        if (Collision.SphereToSphere(touchManager.GetPosX(), touchManager.GetPosY(), 0.0f,xPos, yPos, button.getWidth()/2))
        {
            if(unitSpawned == false && econsManager.getMoney() >= 100) {
                econsManager.setMoney(econsManager.getMoney() - 100);
                unitSpawned = true;
                spawner.Send(Spawner.MSG_TYPE.MSG_SPAWNPLAYER);
            }
            else if(unitSpawned == false && econsManager.getMoney() < 100)
            {
                notEnough = true;
            }

            Log.i("test", "touched");
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        if(!UnitSpawn.instance.notEnough) {
            button = ResourceManager.Instance.GetBitmap(R.drawable.settingicon);
            button = Bitmap.createScaledBitmap(button,(int)(button.getWidth() * .2), (int)(button.getHeight() * .2),false);
            _canvas.drawBitmap(button, xPos - button.getWidth() / 2, yPos - button.getHeight() / 2, null);
        }
        else
        {
            button = ResourceManager.Instance.GetBitmap(R.drawable.settingiconcross);
            button = Bitmap.createScaledBitmap(button,(int)(button.getWidth() * .2), (int)(button.getHeight() * .2),false);
            _canvas.drawBitmap(button, xPos - button.getWidth() / 2, yPos - button.getHeight() / 2, null);
        }
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
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_BTNS;
    }

    public static UnitSpawn Create()
    {
        UnitSpawn result = new UnitSpawn();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BTNS);
        return result;
    }
}
