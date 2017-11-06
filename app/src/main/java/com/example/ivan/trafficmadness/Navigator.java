package com.example.ivan.trafficmadness;

import android.graphics.PointF;

import java.util.List;

/**
 * Created by Ivan on 27.10.2017.
 */

public class Navigator {
    private List<RouteSegment> routeSegments;
    private RouteSegment currentSegment;

    private boolean endOfRoute = false;
    private int sectionNumber;
    private PointF currentPoint;
    private float angle;

    public Navigator(List<RouteSegment> routeSegments) {
        this.routeSegments = routeSegments;
        sectionNumber = 0;
        currentSegment = this.routeSegments.get(sectionNumber);
        currentPoint = currentSegment.getStartPoint();
        angle = 0;
    }

    public PointF getPosition(float speed){
       // angle +=10;
        if(!endOfRoute){
            currentPoint = currentSegment.calculatePosition(speed);
            if(currentSegment.getAngle() != 0) angle = currentSegment.getAngle();
            if(currentSegment.isEnd()){
                sectionNumber++;
                if(sectionNumber < routeSegments.size()){
                    currentSegment = routeSegments.get(sectionNumber);
                } else endOfRoute = true;
            }
        }
        return currentPoint;
    }

    public List<RouteSegment> getRouteSegments() {
        return routeSegments;
    }

    public PointF getCurrentPoint() {
        return currentPoint;
    }

    public float getAngle() {
        return angle;
    }
}