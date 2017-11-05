package com.example.ivan.trafficmadness;

/**
 * Created by Ivan on 27.10.2017.
 */

public interface RouteSegment {
    public Point calculatePosition(float speed);
    public boolean isEnd();
    public Point getStartPosition();
}
