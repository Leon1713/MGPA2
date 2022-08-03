package com.sidm.mgp2021;

import android.view.SurfaceView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EconsManager {
    public static EconsManager instance;
    public float Money;
    private boolean isInit;
    public void Init()
    {
        instance = this;
        isInit = true;
        Money = 200;
    }

    public float getMoney() {
        return Money;
    }

    public void setMoney(float money) {
        Money = money;
    }

}
