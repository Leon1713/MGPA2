package com.sidm.mgp2021;

// Created by TanSiewLan2021

public interface Collidable {
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetRadius();

    int GetWidth();

    int GetHeight();

    void OnHit(Collidable _other);
}

