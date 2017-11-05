package com.example.ivan.trafficmadness;


/**
 * Created by Ivan on 18.10.2017.
 */

public interface Movable extends GameObject {
    public void changeMovingStatus();
    public Point getPosition();
    public float getHeight();
    public float getWidth();
}
