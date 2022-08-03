package com.sidm.mgp2021;


import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import java.lang.annotation.Target;
import java.util.Random;

public class BulletEntity implements EntityBase, Collidable {
    public float speed;
    public SmurfEntityDraggable shooter;
    private boolean isDone = false;
    private float xPos, yPos, offset;
    private Sprite papersprite = null;   // New on Week 8
    private SurfaceView surfaceView;
    private EnemyEntity target;
    private int face;
    private boolean collided;
    private boolean first = true;
    private boolean firstShooter = true;


    Random ranGen = new Random(); //wk 8=>Random Generator

    public EnemyEntity getTarget()
    {
        return target;
    }

    public void setTarget(EnemyEntity target){this.target = target;}

    @Override
    public String GetType()
    {
        return "BulletEntity";
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

    public void setxPos(float x){xPos = x;}

    public void setyPos(float y){yPos = y;}
    @Override
    public void Init(SurfaceView _view) {
        //week 8 => create new sprite instance

        face = -1;
        speed = 700;
        surfaceView = _view;
        papersprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.bullettravelling),1,4, 16 );
        papersprite.Scale(1f,1f);

        //week 8=>randomise position
    }

    public void changeSprite(Sprite sprite)
    {
        papersprite = sprite;
    }
    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) {
            return;
        }


        if(collided)
        {
            collided = false;
            if(papersprite.finished)
            {
                target.health -= 3;
                SetIsDone(true);
            }
        }
        papersprite.Update(_dt);

        if(target.GetPosX() - xPos > 0)
        {
            if(face != 1)
            {
                face = 1;
                papersprite.Flip(true);
            }
        }
        else
        {
            if(face != -1)
            {
                face = -1;
                papersprite.Flip(false);
            }
        }

        //Seek Target
        Simple2DVector tempTarget = new Simple2DVector(target.GetPosX(), target.GetPosY());
        Simple2DVector tempBullet = new Simple2DVector(xPos,yPos);
        Simple2DVector dirToTarget = tempTarget.Mins(tempBullet);
        dirToTarget.normalize();
        xPos += dirToTarget.getX() * speed * _dt ;
        yPos += dirToTarget.getY() * speed *_dt;
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        if(!GameSystem.Instance.GetIsPaused())
        if(target.health > 0)
        papersprite.Render(_canvas, (int)xPos, (int)yPos);
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
        return ENTITY_TYPE.ENT_BULLET;
    } //Week 8=>Update ent type

    public static BulletEntity Create() {
        BulletEntity result = new BulletEntity(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BULLET); //wk8=>update ent type
        return result;
    }

    @Override
    public void OnHit(Collidable _other) {
        if(_other.GetType() != this.GetType()
                && _other.GetType() ==  "EnemyType") {// Another entity
            if(isDone){
                return;
            }
//            if(target.health <= 0)
//            {
//                first = true;
//                isDone = true;
//            }
            if(first) {
                first = false;
                shooter.spawnedBullet = false;
                papersprite = (new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.bulletimpact), 1, 4, 16));
                if(_other.GetPosX() - shooter.GetPosX() > 0)
                {
                    if(shooter.face != 1)
                    {
                        shooter.face = face;
                        shooter.GetSprite().Flip(true);
                    }
                }
                else if(_other.GetPosX() - shooter.GetPosX() < 0)
                {

                    if(shooter.face != -1)
                    {
                        shooter.face = face;
                        shooter.GetSprite().Flip(false);
                    }
                }
            }
            collided = true;

        }
    }
}
