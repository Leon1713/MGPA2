package com.sidm.mgp2021;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.util.LinkedList;
import java.util.List;

public class InBoundsRect {

    SurfaceView surfaceView;
    public List<RectF> rects = new LinkedList<RectF>();
    public static final InBoundsRect instance = new InBoundsRect();
    public void Init(SurfaceView _view) {
    this.surfaceView = _view;
    }
    public void Render(Canvas _canvas)
    {

        final float width = surfaceView.getWidth() * .02f;
        final float height = surfaceView.getHeight() * .02f;
        float padding = 0;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(GamePage.Instance,R.color.green));
        for(int i = 1; i <= 50; ++i)
        {
            for(int p = 1; p <= 50; ++p) {
                float posX = (p - .5f) * width;
                float posY = (i - .5f)* height;
//                Log.i("Height", String.valueOf(height));
//                Log.i("Top: ", String.valueOf((posY - height/2.f)));
//                Log.i("Bottom: ",String.valueOf(posY + height/2));
//                Log.i("PosY: ",String.valueOf(posY));
                if((p >= 16 && p <= 37 && i >= 8 - 1 && i <= 11 + 2) || (p >= 15 && p <= 18 && i >= 9 && i <= 42) || (p>= 15 && p <= 38)) {
                    paint.setColor(ContextCompat.getColor(GamePage.Instance,R.color.red));
                    //rects.add(new RectF((posX - (width * .5f)) + padding,(posY - (height * .5f)) + padding,(posX + (width * .5f)) - padding, (posY + (height * .5f)) - padding));
                    //_canvas.drawRect((posX - (width * .5f)) + padding, (posY - (height * .5f)) + padding,(posX + (width * .5f)) - padding, (posY + (height * .5f)) - padding,paint );
                    continue;
                }
                paint.setColor(ContextCompat.getColor(GamePage.Instance,R.color.green));
                //_canvas.drawRect((posX - (width * .5f)) + padding, (posY - (height * .5f)) + padding,(posX + (width * .5f)) - padding, (posY + (height * .5f)) - padding,paint );
//
            }

        }
    }

}
