package com.sidm.mgp2021;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.ContentInfo;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

public class HealthBarEntity{
    private EnemyEntity enemyEntity;
    private int width, height, margin;
    private Paint BorderPaint, HealthPaint;
    public HealthBarEntity(EnemyEntity enemyEntity)
    {

        this.enemyEntity = enemyEntity;
        width = 100;
        height = 20;
        margin = 2;

        int BorderColor = ContextCompat.getColor(GamePage.Instance, R.color.red);
        BorderPaint = new Paint();
        BorderPaint.setColor(BorderColor);

        int healthColor = ContextCompat.getColor(GamePage.Instance, R.color.green);
        HealthPaint = new Paint();
        HealthPaint.setColor(healthColor);


    }
    public void Render(Canvas _canvas)
    {
        float x = enemyEntity.GetPosX();
        float y = enemyEntity.GetPosY();
        float distToEnemy = 30;
        float healthPercent = enemyEntity.health/enemyEntity.MAX_HEALTH;

        float borderLeft, borderRight, borderTop, borderBottom;
        borderLeft = x - width/2;
        borderRight = x + width/2;
        borderBottom = y - distToEnemy;
        borderTop = borderBottom - height;
        _canvas.drawRect(borderLeft,borderTop,borderRight,borderBottom,BorderPaint);

        float healthLeft, healthRight, healthTop, healthBottom,healthWidth,healthHeight;
        healthWidth = width - 2 * margin;
        healthHeight = height - 2 * margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth * healthPercent;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        _canvas.drawRect(healthLeft,healthTop,healthRight,healthBottom,HealthPaint);
    }
}
