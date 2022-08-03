package com.sidm.mgp2021;

import android.graphics.Canvas;
import android.icu.number.Scale;
import android.util.Log;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.util.Random;

public class SmurfEntityDraggable implements EntityBase {
    private boolean isDone = false;
    private float xPos, yPos, offset;
    private Sprite spritesmurf = null;   // New on Week 8
    private SurfaceView surfaceView = null;
    float TimeElasped1 = 0.f;
    public int face = -1;
    public int Switch1 = 0; // 0 : Idle , 1 : Shooting bmp
    public boolean isShooting = false;
    public boolean isPlaced = false;
    public boolean spawnedBullet;
    private boolean canPlace;
    private float timeElasped = 0.f;
    public float shootCoolDown = 2.f;
    public boolean isIdling = true;
    public boolean firstShoot = true;
    public EntityBase target;
    public boolean enemyDED;
    public GridIndicator gridIndicator;
    private UnitSpawn unitSpawn;
    private boolean firstisPlaced = true;
    private int[] arrX;
    private int[] arrY;


    Random ranGen = new Random(); //wk 8=>Random Generator

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public Sprite GetSprite(){return spritesmurf;}

    public float GetPosX(){return xPos;}
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
        canPlace = true; // determine can be placed
        surfaceView = _view;
        spritesmurf = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.idleturret),1,13, 8 );
        spritesmurf.Scale(0.4f,0.4f);
        //week 8=>randomise position
        xPos = TouchManager.Instance.GetPosX();
        yPos = TouchManager.Instance.GetPosY();
        spawnedBullet = false;
        firstisPlaced = true;
        gridIndicator = new GridIndicator((int)( surfaceView.getWidth()/20.f),(int) (surfaceView.getHeight()/20.f),xPos,yPos);
        arrX = new int[]{16,37,16,37,21,37,21,32,52,32};
        arrY = new int[]{9,9,9,42,17,42,24,33,24,33};
    }

    public float getTimeElasped() {
        return timeElasped;
    }

    public void setTimeElasped(float timeElasped) {
        this.timeElasped = timeElasped;
    }

    @Override
    public void Update(float _dt) {

        if(UnitSpawn.instance != null)
        {
            unitSpawn = UnitSpawn.instance;
        }
        if(GameSystem.Instance.GetIsPaused()) {
            return;
        }

        if(spawnedBullet == false && target != null)
        {
            if(this.getTimeElasped() >= this.shootCoolDown)
            EntityManager.spawner.Send(Spawner.MSG_TYPE.MSG_SPAWNBULLET,target,this);
        }

        if (TouchManager.Instance.HasTouch() && !isPlaced)  // Touch and drag
        {
            // Check collision with the smurf sprite
            float imgRadius1 = spritesmurf.GetWidth() * 0.5f;
            //Log.v("imgrad","s"+imgRadius1);
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius1) ) {
                // set it to nearest grid assuming grid is 100

                isPlaced = false;
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();
                gridIndicator.setPosX(((surfaceView.getWidth()/20) * (int)(xPos/(surfaceView.getWidth()/20))) + (surfaceView.getWidth()/20)/2);
                gridIndicator.setPosY(((surfaceView.getHeight()/20) * (int)(yPos/(surfaceView.getHeight()/20))) + (surfaceView.getHeight()/20)/2);

            }
            String value = '(' +String.valueOf(TouchManager.Instance.GetPosX()) + ',' + String.valueOf(TouchManager.Instance.GetPosY()) +')';
            Log.i("Touch Position",value );

        }
        else if(canPlace) {
            isPlaced = true;
            if (firstisPlaced && UnitSpawn.instance!= null )
            {
                firstisPlaced = false;
                unitSpawn.unitSpawned = false;
            }

            xPos = ((surfaceView.getWidth()/20) * (int)(xPos/(surfaceView.getWidth()/20))) + (surfaceView.getWidth()/20)/2;
            yPos = ((surfaceView.getHeight()/20) * (int)(yPos/(surfaceView.getHeight()/20))) + (surfaceView.getHeight()/20)/2;
            String value = '(' +String.valueOf((int)(xPos/(surfaceView.getWidth()/50))) + ',' + String.valueOf((int)(yPos/(surfaceView.getHeight()/50))) +')';



        }

        if(target != null)
        {
            if(target.IsDone()) {
                enemyDED = true;
                target = null;
            }
        }
        if(isShooting == false && target != null && isPlaced == true)
        {
            enemyDED = false;


            {
             isShooting = true;
             changeSprite(new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.turretattack),1,4,8 ));
             face = -1;
             spritesmurf.Scale(.4f,.4f);
            }
        }
        else if((isShooting == true) && target == null)
        {
            isShooting = false;
            changeSprite(new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.idleturret),1,13, 8));
            face = -1;
            if(enemyDED)
            {
                spritesmurf.Flip(true);
            }
            spritesmurf.Scale(.4f,.4f);


        }
        // wk8=> update sprite animation frame based on timing
        spritesmurf.Update(_dt);
        TimeElasped1+=_dt;


        timeElasped += _dt;
        //Wk8=>End Dragging Code
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        if(!GameSystem.Instance.GetIsPaused()) {
            if (TouchManager.Instance.HasTouch())
                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, spritesmurf.GetWidth() / 2) && !isPlaced){
                    String a = String.valueOf(0);


                            if (true) {
                                a = String.valueOf(TouchManager.Instance.GetPosY());
                                gridIndicator.setGridColor(ContextCompat.getColor(GamePage.Instance, R.color.red));
                            }
                            else {
                                gridIndicator.setGridColor(ContextCompat.getColor(GamePage.Instance, R.color.green));
                            }


                    Log.i("Touch(ss): ", a);
                    gridIndicator.Render(_canvas);
                }

            spritesmurf.Render(_canvas, (int) xPos, (int) yPos);
        }
    }

    public void changeSprite(Sprite sprite)
    {
        spritesmurf = sprite;
    }

    @Override
    public boolean IsInit() {
        return spritesmurf != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.SMURF_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PLAYER;
    } //Week 8=>Update ent type

    public static SmurfEntityDraggable Create() {
        SmurfEntityDraggable result = new SmurfEntityDraggable(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PLAYER); //wk8=>update ent tyep
        return result;
    }

    public float GetPosY() {
        return yPos;
    }
}