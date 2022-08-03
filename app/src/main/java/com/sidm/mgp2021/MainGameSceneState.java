package com.sidm.mgp2021;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        PausebuttonEntity.Create();
        //SmurfEntityDraggable.Create();
        //EnemyEntity.Create();
        PlayerHealth.Create();
        PlayerDamageManager.Create();
        UnitSpawn.Create();
        RenderTextEntity.Create(); //<--Add this to render text
        PauseRenderEntity.Create();
        TutorialRenderEntity.create();
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
        InBoundsRect.instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {
        EntityManager.Instance.Update(_dt);
        EntityManager.spawner.Update(_dt);
        if (TouchManager.Instance.IsDown()) {
            //Example of touch on screen in the main game to trigger back to Main menu
           // StateManager.Instance.ChangeState("Shop");
        }
    }
}



