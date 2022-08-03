package com.sidm.mgp2021;

import android.graphics.Canvas;
import android.os.VibratorManager;
import android.view.SurfaceView;

import java.lang.annotation.Target;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

// Created by TanSiewLan2021

public class EntityManager {

    public static Spawner spawner = new Spawner();
    public final static EntityManager Instance = new EntityManager();
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();
    private SurfaceView view = null;
    private int face;
    private boolean first = true;
    private boolean firstShoot = true;


    private EntityManager()
    {
    }

    public void Init(SurfaceView _view)
    {
        view = _view;
    }

    public void Update(float _dt)
    {
        if(spawner.IsInit() == false) {
            spawner.Init(view);
        }
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        // Update all
        for(int i = 0; i < entityList.size(); ++i)
        {
            // Lets check if is init, initialize if not
            if (!entityList.get(i).IsInit())
            {
                entityList.get(i).Init(view);
            }

            entityList.get(i).Update(_dt);

            // Check if need to clean up
            if (entityList.get(i).IsDone()) {
                // Done! Time to add to the removal list
                removalList.add(entityList.get(i));
            }
        }

        for (EntityBase currEntity : entityList)
        {
            // Lets check if is init, initialize if not
            if (!currEntity.IsInit())
                currEntity.Init(view);
            //flip
            for(int i = 0; i < entityList.size(); ++i)
            {
                if(currEntity.GetEntityType() == EntityBase.ENTITY_TYPE.ENT_PLAYER && entityList.get(i).GetEntityType() == EntityBase.ENTITY_TYPE.ENT_ENEMY && entityList.get(i).IsDone() == false && currEntity.IsDone() == false)
                {
                    SmurfEntityDraggable tempp = (SmurfEntityDraggable)currEntity;
                    EnemyEntity temp = (EnemyEntity) entityList.get(i);
                    EnemyEntity target = null;
                    Simple2DVector targetvector = new Simple2DVector(100000,1000000);
                    Simple2DVector turret = new Simple2DVector(tempp.GetPosX(),tempp.GetPosY());
                    Simple2DVector enemy = new Simple2DVector(temp.GetPosX(),temp.GetPosY());
                    if(tempp.target != null)
                    {
                        target = (EnemyEntity) tempp.target;
                        targetvector = new Simple2DVector(target.GetPosX(),target.GetPosY());
                        if(target.GetPosX() - tempp.GetPosX() > 0 )
                        {
                            face = 1;
                            if(tempp.face != face)
                            {
                                tempp.face = face;
                                tempp.GetSprite().Flip(true);
                            }
                        }
                        else if(target.GetPosX() - tempp.GetPosX() < 0)
                        {
                            face = -1;
                            if(tempp.face != face)
                            {
                                tempp.face = face;
                                tempp.GetSprite().Flip(true);
                            }
                        }

                    }

                    if((enemy.Mins(turret).length()) <= 300)
                    {
                        //spawn bullet
                       if(tempp.isPlaced && tempp.target == null )
                        {

                            first = true;
                            //spawner.Send(Spawner.MSG_TYPE.MSG_SPAWNBULLET,temp,tempp);
                            //put this in update in smurf
                            if(temp.health > 0)
                            tempp.target = temp;
//                            else
//                                tempp.target = null;
//                            .if(tempp.Switch1 == 0 && !tempp.isShooting) {
//                                tempp.Switch1 = 1;
//                                tempp.isShooting = true;
//                                tempp.isIdling = false;
                                //tempp.changeSprite(new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.turretattack), 1, 4, 8));
//                           }
//                            tempp.face = -1;
//                            if(temp.GetPosX() - tempp.GetPosX() > 0)
//                            {
//                                face = 1;
//                                if(tempp.face != face)
//                                {
//                                    tempp.face = face;
//                                    tempp.GetSprite().Flip(true);
//                                }
//                            }
//                            else if(temp.GetPosX() - tempp.GetPosX() < 0)
//                            {
//                                face = -1;
//                                if(tempp.face != face)
//                                {
//                                    tempp.face = face;
//                                    tempp.GetSprite().Flip(false);
//                                }
//                            }
//                            tempp.GetSprite().Scale(0.4f,0.4f);
//
                        }
                        //send bullet
                        //temp.health -= temp.health/3f *_dt; // test
                    }
                    else if(tempp.target == null ||tempp.target != null && (targetvector.Mins(turret)).length()>300)
                    {
                        tempp.target = null;
                        if(first)
                        {
//                            first = false;
//                            tempp.changeSprite(new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.idleturret),1,13,8));
//                            tempp.face = -1;
//                            if(temp.GetPosX() - tempp.GetPosX() > 0)
//                            {
//                                face = 1;
//                                if(tempp.face != face)
//                                {
//                                    tempp.face = face;
//                                    tempp.GetSprite().Flip(true);
//                                }
//                            }
//                            else if(temp.GetPosX() - tempp.GetPosX() < 0)
//                            {
//                                face = -1;
//                                if(tempp.face != face)
//                                {
//                                    tempp.face = face;
//                                    tempp.GetSprite().Flip(false);
//                                }
//                            }
//                            tempp.GetSprite().Scale(0.4f,0.4f);

                        }
                    }

                }
            }
            currEntity.Update(_dt);

            // Check if need to clean up
            if (currEntity.IsDone()) {
                // Done! Time to add to the removal list
                removalList.add(currEntity);
            }
        }

        // Remove all entities that are done
        for (EntityBase currEntity : removalList) {
            entityList.remove(currEntity);
        }
        removalList.clear(); // Clean up of removal list

        // Collision Check
        for (int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if (currEntity instanceof Collidable)
            {
                Collidable first = (Collidable) currEntity;

                for (int j = i+1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if (otherEntity instanceof Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;

                        if (Collision.AABB(first.GetPosX(),first.GetPosY(),second.GetPosX(), second.GetPosY(),first,second))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }

            // Check if need to clean up
            if (currEntity.IsDone()) {
                removalList.add(currEntity);
            }
        }

        // Remove all entities that are done
        for (EntityBase currEntity : removalList) {
            entityList.remove(currEntity);
        }
        removalList.clear();
    }

    public void Render(Canvas _canvas)
    {
      
        // Use the new "rendering layer" to sort the render order
        Collections.sort(entityList, new Comparator<EntityBase>() {
            @Override
            public int compare(EntityBase o1, EntityBase o2) {
                return o1.GetRenderLayer() - o2.GetRenderLayer();
            }
        });

        for(int i = 0; i <entityList.size(); ++i)
        {
            entityList.get(i).Render(_canvas);
        }
    }

    public void AddEntity(EntityBase _newEntity, EntityBase.ENTITY_TYPE entity_type)
    {
        entityList.add(_newEntity);
    }

    public void Clean()
    {
        entityList.clear();
    }
}


