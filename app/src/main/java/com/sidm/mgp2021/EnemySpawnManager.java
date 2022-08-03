package com.sidm.mgp2021;

import android.content.Intent;

public class EnemySpawnManager {
    int round;
    int MaxEnemy;
    float EnemySpawnCD;
    int SpawnBetweenEnemy;
    public boolean isTutorial;
    float BetweenCD;
    float TimeElaspedTick = 0.f;
    float TimeElaspedRound = 0.f;
    boolean Roundbool;
    boolean Tickbool;
    float enemyCount;
    int maxRound = 20; // for test;
    EnemySpawnManager instance;
    Intent intent;
    public EnemySpawnManager(int round)
    {
        if(round == 0)
        {
            intent = new Intent();
            isTutorial = true;
            Roundbool = false;
            Tickbool = false;
            round = round;
            MaxEnemy = 7;
            EnemySpawnCD = 3;
            SpawnBetweenEnemy = round;
            BetweenCD = 3;
            enemyCount = MaxEnemy;

        }
        else {


            // instance = this;
            Roundbool = false;
            Tickbool = false;
            round = round;
            MaxEnemy = round * 10;
            EnemySpawnCD = 3 / round;
            SpawnBetweenEnemy = round;
            BetweenCD = round / 10.f;
            enemyCount = MaxEnemy;
        }
    }
    public EnemySpawnManager()
    {
        Roundbool = false;
        Tickbool = false;
        round = 1;
        MaxEnemy = Integer.MAX_VALUE;
        EnemySpawnCD = 3/round;
        SpawnBetweenEnemy = round;
        BetweenCD = round/10.f;
        enemyCount = MaxEnemy;
    }
    public void SpawnBetweenTicks(float _dt)
    {
        if(TimeElaspedTick >= BetweenCD)
        TimeElaspedTick = 0;
    }
    public void Update(float _dt)
    {
        if(enemyCount <= 0)
        {
            if(MaxEnemy >= 20)
            {
                StateManager.Instance.ChangeState("Gameoverscreen");
                intent.setClass(GamePage.Instance,Gameoverscreen.class);
                GamePage.Instance.startActivity(intent);
                return;
            }
            ++MaxEnemy;
            enemyCount = MaxEnemy;
            return;
        }

        if(TimeElaspedRound >= EnemySpawnCD)
        {
            EntityManager.spawner.Send(Spawner.MSG_TYPE.MSG_SPAWNENEMY);
            TimeElaspedRound = 0;
            --enemyCount;
        }
        TimeElaspedTick+= _dt;
        TimeElaspedRound += _dt;
    }
}
