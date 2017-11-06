package com.example.ivan.trafficmadness;


import android.graphics.PointF;

/**
 * Created by Ivan on 18.10.2017.
 */

public interface Movable extends GameObject {
    public void changeMovingStatus();
    public PointF getPosition();
    public float getHeight();
    public float getWidth();
}
