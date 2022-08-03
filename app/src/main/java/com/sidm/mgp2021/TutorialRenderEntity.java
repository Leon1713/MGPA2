package com.sidm.mgp2021;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.Switch;

import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

public class TutorialRenderEntity implements EntityBase{
    boolean isDone;
    SurfaceView view;
    boolean isInit;
    Bitmap arrow;
    String text;
    float aPosx, aPosy;
    boolean isTouch;
    int stage;
    float timeElasped = 0;
    Typeface myfont;

    //2. we point arrow at hearts.

    int ScreenWidth, ScreenHeight;

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

        isTouch = false;
        isInit = true;
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();

        TutorialManager.instance.setTutorial(true);
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        stage = 1;
        arrow = ResourceManager.Instance.GetBitmap(R.drawable.arrow);
        view = _view;

        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/digital-7.ttf");
    }

    @Override
    public void Update(float _dt) {
        if(stage <= 4 && timeElasped >= 0)
        {
            GameSystem.Instance.SetIsPaused(true);
            TutorialManager.instance.setTutorial(true);
        }
        else
        {
            if(stage == 5) {
                GameSystem.Instance.SetIsPaused(false);
                isDone = true;
            }
            TutorialManager.instance.setTutorial(false);
        }
        if(TouchManager.Instance.HasTouch() && !isTouch)
        {
            isTouch = true;
            ++stage;
        }
        if(!TouchManager.Instance.HasTouch())
        {
            isTouch = false;
        }
        timeElasped += _dt;
        // if Touch detected move to next stage
        // render UI according to stage.
    }

    @Override
    public void Render(Canvas _canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(GamePage.Instance,R.color.white));
        //paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(50);
        Bitmap temp;
        switch (stage)
        {
            case 1:
                _canvas.drawText("This is the health which" +
                        " will decrease when enemy reaches goal.",400,204,paint);
                temp = Bitmap.createScaledBitmap(arrow,-ScreenWidth/12,ScreenHeight/12,false);
                aPosx = 300;
                aPosy = 204;
                break;
            case 2:
                _canvas.drawText("Drag units out of here to place on to field," +
                        " each unit costs $100.",400,ScreenHeight - 170,paint);
                temp = Bitmap.createScaledBitmap(arrow,-ScreenWidth/12,ScreenHeight/12,false);
                aPosy = ScreenHeight - 170;
                aPosx = 300;
                break;
            case 3:
                _canvas.drawText("This is your money",ScreenWidth/2 + 190,ScreenHeight - 120,paint);

                temp = Bitmap.createScaledBitmap(arrow,ScreenWidth/12,ScreenHeight/12,false);

                aPosx = ScreenWidth - 420;
                aPosy = ScreenHeight - 120;
                break;
            case 4:
                _canvas.drawText("Don't let enemies reach this place (-1 health each)",ScreenWidth/2 -140,ScreenHeight/2,paint);
                temp = Bitmap.createScaledBitmap(arrow,ScreenWidth/12,ScreenHeight/12,false);
                aPosy = ScreenHeight/2;
                aPosx = ScreenWidth - temp.getWidth()/2;
                break;
            default:
                temp = Bitmap.createScaledBitmap(arrow,-ScreenWidth/12,ScreenHeight/12,false);
                break;
        }
        _canvas.drawBitmap(temp,aPosx - (temp.getWidth()/2), aPosy - (temp.getHeight()/2),null);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.UI_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
    }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PAUSE;
    }

    public static TutorialRenderEntity create()
    {
        TutorialRenderEntity result = new TutorialRenderEntity();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_PAUSE);
        return result;
    }
}
