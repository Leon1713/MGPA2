package com.sidm.mgp2021;

// Created by TanSiewLan2021

import org.w3c.dom.Entity;

public class Collision {

    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }
    public static boolean AABB(float x1, float y1, float x2, float y2, Collidable entity1, Collidable entity2)
    {
        if (x1 < x2 + entity2.GetWidth() &&
                x1 + entity1.GetWidth() > x2 &&
                y1 < y2 + entity2.GetHeight() &&
                entity1.GetHeight() + y1 > y2) {
            return true;
        }
        else
            return false;
    }
}
