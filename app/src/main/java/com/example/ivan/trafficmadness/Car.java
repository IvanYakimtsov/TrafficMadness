package com.example.ivan.trafficmadness;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Ivan on 18.10.2017.
 */

public interface Car extends GameObject {
    public void changeMovingStatus();
    public float getX();
    public float getY();
    public float getHigh();
    public float getWidth();
}
