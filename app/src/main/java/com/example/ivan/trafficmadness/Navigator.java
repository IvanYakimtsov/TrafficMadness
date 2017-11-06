package com.example.ivan.trafficmadness;

import android.graphics.PointF;

import java.util.List;

/**
 * Created by Ivan on 27.10.2017.
 */

public class Navigator {
    List<RouteSegment> routeSegments;
    RouteSegment currentSegment;

    boolean endOfRoute = false;
    int sectionNumber;
    PointF currentPoint;

    public Navigator(List<RouteSegment> routeSegments) {
        this.routeSegments = routeSegments;
        sectionNumber = 0;
        currentSegment = this.routeSegments.get(sectionNumber);
        currentPoint = currentSegment.getStartPoint();
    }

    public PointF getPosition(float speed){
        if(!endOfRoute){
            currentPoint = currentSegment.calculatePosition(speed);
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
}