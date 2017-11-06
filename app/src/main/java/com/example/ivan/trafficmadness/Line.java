package com.example.ivan.trafficmadness;

import android.graphics.PointF;
import android.util.Log;

/**
 * Created by Ivan on 27.10.2017.
 */

public class Line implements RouteSegment {
    private PointF currentPoint;
    private PointF startPoint;
    private PointF endPoint;
    private float angle;

    public Line(PointF startPoint, PointF endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.currentPoint = new PointF(this.startPoint.x, this.startPoint.y);
        calculateAngle();
    }

    private void calculateAngle() {
        //  float dx = endPoint.x - currentPoint.x;
        //    float dy = endPoint.y - currentPoint.y;
//        if(dx == 0) dx = 1;
        //TODO: calculate angle here
        if(endPoint.x < startPoint.x) angle = 180;
        else angle = 0;
    }

    @Override
    public PointF calculatePosition(float speed) {
        //находим длину исходного отрезка
        float dx = endPoint.x - currentPoint.x;
        float dy = endPoint.y - currentPoint.y;
//        if(dx == 0) dx = 1;
//        angle = dy / dx;
//        Log.d("check",angle+"");
        // if(angle < 0)
        double l = Math.sqrt(dx * dx + dy * dy);
        if (speed > l) speed = (float) l;
        //находим направляющий вектор
        double dirX = dx / l;
        double dirY = dy / l;
        //умножаем направляющий вектор на необх длину
        dirX *= speed;
        dirY *= speed;
        //находим точку
        currentPoint.x = (float) (dirX + currentPoint.x);
        currentPoint.y = (float) (dirY + currentPoint.y);
        return currentPoint;
    }

    @Override
    public boolean isEnd() {
        return (currentPoint.x == endPoint.x && currentPoint.y == endPoint.y);
    }

    @Override
    public PointF getStartPoint() {
        return startPoint;
    }

    @Override
    public PointF getEndPoint() {
        return endPoint;
    }

    @Override
    public float getAngle() {
        return angle;
    }

}