package com.sidm.mgp2021;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class GridIndicator {
    private int width, height,gridColor = ContextCompat.getColor(GamePage.Instance,R.color.green);
    private float posX, posY;
    private Paint gridPaint = new Paint();
    public GridIndicator(int width,int height,float posX,float posY)// grid == screenwidth/50
    {
        gridPaint.setColor(gridColor);
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setGridColor(int gridColor)
    {
        gridPaint.setColor(gridColor);
    }
    public void Render(Canvas _canvas)
    {
        _canvas.drawRect(posX - width/2,posY - height/2,posX + width/2,posY + height/2,gridPaint);
    }

}
