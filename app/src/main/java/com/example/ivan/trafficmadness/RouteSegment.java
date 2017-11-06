package com.example.ivan.trafficmadness;

import android.graphics.PointF;

/**
 * Created by Ivan on 27.10.2017.
 */

public interface RouteSegment {
    public PointF calculatePosition(float speed);
    public boolean isEnd();
    public PointF getStartPoint();
    public PointF getEndPoint();
}