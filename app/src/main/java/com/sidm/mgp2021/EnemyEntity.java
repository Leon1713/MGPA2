package com.sidm.mgp2021;


import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class EnemyEntity implements EntityBase, Collidable {
    public float MAX_HEALTH;
    public float health;
    public int level = 1;
    public float speed = 3.f;
    private boolean isDone = false;
    private float xPos, yPos, offset;
    private Sprite papersprite = null;   // New on Week 8
    private SurfaceView surfaceView = null;
    private int waypoints = 0;
    private HealthBarEntity healthBarEntity;


    Random ranGen = new Random(); //wk 8=>Random Generator


    @Override
    public String GetType()
    {
        return "EnemyType";
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
        return papersprite.GetWidth();
    }

    @Override
    public int GetWidth()
    {
        return papersprite.GetWidth();
    }
    @Override
    public int GetHeight()
    {
        return papersprite.GetHeight();
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
        //week 8 => create new sprite instance
        healthBarEntity = new HealthBarEntity(this);

        surfaceView = _view;
        papersprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.paper),1,1, 1 );
        papersprite.Scale(0.1f,0.1f);
        //week 8=>randomise position
        xPos = 37 * (_view.getWidth()/50) + (_view.getWidth()/50)/2;
        yPos = 0;
        health = 10;
        MAX_HEALTH = 10;

    }

    public void changeSprite(Sprite sprite)
    {
        papersprite = sprite;
    }
    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused() || waypoints == 10 || this == null) {
            return;
        }
        if(Math.round(health)<= 0)
        {

            EconsManager.instance.setMoney(EconsManager.instance.getMoney() + 20.f);
            health = 0;
            ScoreManager.instance.setScore(ScoreManager.instance.getScore() + 10);
            isDone = true;
        }
        // wk8=> update sprite animation frame based on timing

        papersprite.Update(_dt);

        //move down


        // Moving along pathway
        switch (waypoints){
            case 0:
                yPos += speed * surfaceView.getHeight()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 37 && (int)(yPos/(surfaceView.getHeight()/50)) == 9 )
                {
                    ++waypoints;
                }
                break;
            case 1:
               xPos -= speed * surfaceView.getWidth()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 16 && (int)(yPos/(surfaceView.getHeight()/50)) == 9 )
                {
                    ++waypoints;
                }
                break;
            case 2:
                yPos += speed * surfaceView.getHeight()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 16 && (int)(yPos/(surfaceView.getHeight()/50)) == 42 )
                {
                    ++waypoints;
                }
                break;
            case 3:
                xPos += speed *surfaceView.getWidth()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 37 && (int)(yPos/(surfaceView.getHeight()/50)) == 42 )
                {
                    ++waypoints;
                }
                break;
            case 4:
                yPos -= speed *surfaceView.getHeight()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 37 && (int)(yPos/(surfaceView.getHeight()/50)) == 17 )
                {
                    ++waypoints;
                }
                break;
            case 5:
                xPos -= speed *surfaceView.getWidth()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 21 && (int)(yPos/(surfaceView.getHeight()/50)) == 17 )
                {
                    ++waypoints;
                }
                break;
            case 6:
                yPos += speed *surfaceView.getHeight()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 21 && (int)(yPos/(surfaceView.getHeight()/50)) == 33 )
                {
                    ++waypoints;
                }
                break;
            case 7:
                xPos += speed *surfaceView.getWidth()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 32 && (int)(yPos/(surfaceView.getHeight()/50)) == 33 )
                {
                    ++waypoints;
                }
                break;
            case 8:
                yPos -= speed *surfaceView.getHeight()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 32 && (int)(yPos/(surfaceView.getHeight()/50)) == 24 )
                {
                    ++waypoints;
                }
                break;
            case 9:
                xPos += speed *surfaceView.getHeight()/50 * _dt;
                if((int)(xPos/(surfaceView.getWidth()/50)) == 52 && (int)(yPos/(surfaceView.getHeight()/50)) == 24 )
                {
                    ++waypoints;
                }
                break;
            case 10:
                //Destroy()
                return;
        }

        //wk8=>Dragging code --

        //Wk8=>End Dragging Code
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int

        if(!GameSystem.Instance.GetIsPaused()) {
            papersprite.Render(_canvas, (int) xPos, (int) yPos);
            healthBarEntity.Render(_canvas);
        }
    }

    @Override
    public boolean IsInit() {
        return papersprite != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.GAMEOBJECTS_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_ENEMY;
    } //Week 8=>Update ent type

    public static EnemyEntity Create() {
        EnemyEntity result = new EnemyEntity(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ENEMY); //wk8=>update ent type
        return result;
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType() != this.GetType()
                && _other.GetType() ==  "BulletEntity") {  // Another entity

        }
    }
}
