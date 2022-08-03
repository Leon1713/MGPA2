package com.sidm.mgp2021;

import android.graphics.Canvas;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.annotation.Target;
import java.sql.Time;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Spawner implements EntityBase {
    boolean isInit = false;
    private EntityBase sender;
    private EntityBase target;
    private MSG_TYPE msg_type;
    private SurfaceView view;
    private TouchManager touchManager;
    private float TimeElasped = 0.f;
    public Spawner instance = this;
    SmurfEntityDraggable player;
    EnemyEntity enemyEntity;
    private Queue<Spawner> messageQueue = new LinkedList<>(); // Spawning of enemies
    private Queue<Spawner> bulletQueue = new LinkedList<>(); // spawning of Bullets
    private Queue<Spawner> playerQueue = new LinkedList<>(); // Spawning of Players

    @Override
    public boolean IsDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {

    }

    @Override
    public void Init(SurfaceView _view) {
        isInit = true;
        view = _view;
    }

    @Override
    public void Update(float _dt) {

        if (GameSystem.Instance.GetIsPaused()) {
            return;
        }




            if (!bulletQueue.isEmpty()) {
                player = (SmurfEntityDraggable) bulletQueue.peek().sender;
                enemyEntity = (EnemyEntity) bulletQueue.peek().target;
                if (player.getTimeElasped() >= player.shootCoolDown) {

                    if (player.spawnedBullet == false) {
                        player.spawnedBullet = true;
                        BulletEntity tempBullet = BulletEntity.Create();
                        if (tempBullet.IsInit() == false) {
                            tempBullet.Init(view);
                        }
                        tempBullet.setxPos(player.GetPosX());
                        tempBullet.setyPos(player.GetPosY());
                        tempBullet.shooter = player;
                        tempBullet.setTarget(enemyEntity);
                        AudioManager.instance.PlayAudio(R.raw.laser7,100);
                    }
                    player.setTimeElasped(0.f);
                }
                bulletQueue.remove();
                Log.i("Size","" + bulletQueue.size());
            }


            if (!playerQueue.isEmpty())
            {
                SmurfEntityDraggable tempPlayer = SmurfEntityDraggable.Create();
                if (tempPlayer.IsInit() == false) {
                    tempPlayer.Init(view);
                }
                tempPlayer.setxPos(TouchManager.Instance.GetPosX());
                tempPlayer.setyPos(TouchManager.Instance.GetPosY());
                playerQueue.poll();
            }

            if (!messageQueue.isEmpty())
            {
                EnemyEntity enemy = EnemyEntity.Create();
                if (enemy.IsInit() == false)
                {
                    enemy.Init(view);
                }
                messageQueue.poll();

            }
            //TimeElasped += _dt;

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return 0;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {

    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return null;
    }

    enum MSG_TYPE {
        MSG_SPAWNBULLET,
        MSG_SPAWNPLAYER,
        MSG_SPAWNENEMY,
        MSG_SPAWNSOMEOTHERSTUFF,
    }

    ;

    void Send(MSG_TYPE msg_type, EntityBase target, EntityBase sender) {
        Spawner temp = new Spawner();
        temp.msg_type = msg_type;
        temp.target = target;
        temp.sender = sender;
        bulletQueue.add(temp);
    }

    void Send(MSG_TYPE msg_type) {
        Spawner temp = new Spawner();
        if (msg_type != MSG_TYPE.MSG_SPAWNBULLET) {
            temp.msg_type = msg_type;
            temp.sender = null;
            temp.target = null;
            if(msg_type == MSG_TYPE.MSG_SPAWNENEMY)
            messageQueue.add(temp);
            else
                playerQueue.add(temp);
        }
    }
}
