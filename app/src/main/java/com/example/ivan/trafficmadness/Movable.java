package com.example.ivan.trafficmadness;


import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Ivan on 18.10.2017.
 */

public interface Movable extends GameObject {
    public void changeMovingStatus();
    public PointF getPosition();
    public RectF getBounds();
}
