package com.sidm.mgp2021;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Sprite {
    private int row = 0;
    private int col = 0;
    private int width = 0;
    private int height = 0;

    private Bitmap bmp = null;

    private int currentFrame = 0;
    private int startFrame = 0;
    private int endFrame = 0;

    private float timePerFrame = 0.0f;
    private float timeAcc = 0.0f;
    public  boolean finished = false;

    public Sprite(Bitmap _bmp, int _row, int _col, int _fps)
    {
        bmp = _bmp;
        row = _row;
        col = _col;

        width = bmp.getWidth() / _col;
        height = bmp.getHeight() / _row;

        timePerFrame = 1.0f / (float)_fps;

        endFrame = _col * _row;

    }

    public Bitmap getBmp() {
        return bmp;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public void Flip(boolean x)
    {
        Matrix matrix = new Matrix();
        matrix.postScale(x?  -1f : 1f,1,bmp.getWidth()/2f,bmp.getHeight()/2);
        bmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
    }
    public void Update(float _dt)
    {
        timeAcc += _dt;
        if (timeAcc > timePerFrame)
        {
            ++currentFrame;
            if (currentFrame >= endFrame) {
//
                finished = true;
                currentFrame = startFrame;
            }
            timeAcc = 0.0f;

        }
    }

    public void Scale(float _x, float _y)
    {
        bmp = Bitmap.createScaledBitmap(bmp,(int)(bmp.getWidth() * _x),(int)(bmp.getHeight() * _y),false);
        width = bmp.getWidth() / col;
        height = bmp.getHeight() / row;
    }
    public void Render(Canvas _canvas, int _x, int _y)
    {
        int frameX = currentFrame % col;
        int frameY = currentFrame / col;
        int srcX = frameX * width;
        int srcY = frameY * height;

        _x -= 0.5f * width;
        _y -= 0.5f * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(_x, _y, _x + width, _y + height);
        _canvas.drawBitmap(bmp, src, dst, null);
    }

    public void SetAnimationFrames(int _start, int _end)
    {
        timeAcc = 0.0f;
        currentFrame = _start;
        startFrame = _start;
        endFrame = _end;
    }

    public int GetHeight()
    {
        return height;
    }

    public int GetWidth()
    {
        return width;
    }
}
