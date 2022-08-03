package com.sidm.mgp2021;

public class Simple2DVector {
    private float x, y;
    public Simple2DVector(float x,float y)
    {
        this.x = x;
        this.y = y;
    }
    public float length()
    {
        float lengthSquared = (float) (Math.pow(x,2) + Math.pow(y,2));
        float length = (float) (Math.sqrt((double)lengthSquared));
        return length;
    }
    public Simple2DVector Mins(Simple2DVector temp)
    {
       return new Simple2DVector(this.x - temp.x,this.y - temp.y);
    }
    public void normalize()
    {
        try
        {

            {
                x /= length();
                y /= length();
            }
        }
        catch(Exception divideByZero)
        {
            x = 1;
            y = 0;
        }


    }
    public void setX(float xVal)
    {
        x = xVal;
    }
    public void setY(float yVal)
    {
        y = yVal;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
}
