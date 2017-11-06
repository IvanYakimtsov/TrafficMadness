package com.example.ivan.trafficmadness;

import android.graphics.PointF;
import android.util.Log;


/**
 * Created by Ivan on 27.10.2017.
 */

public class Turn implements RouteSegment {
    private PointF startPoint;
    private float radius;
    private float angle;
    private float currentAngle;

    private PointF rotationPoint;
    private PointF currentPoint;
    private PointF endPoint;
    private boolean clockWise;

    public Turn(PointF startPoint, float radius,float startAngel,float angle) {
        this.startPoint = startPoint;
        this.radius = radius;
        if(angle < 0) {
            clockWise = false;
        }
        else clockWise = true;
        this.angle = Math.abs(angle);
        currentAngle = Math.abs(startAngel);

        this.rotationPoint = new PointF((startPoint.x - radius), startPoint.y);

        this.currentPoint = new PointF(startPoint.x, startPoint.y);

        calculateEndPoint();
    }


    private void calculateEndPoint(){
        float dx = startPoint.x - rotationPoint.x;
        float dy = startPoint.y - rotationPoint.y;
        float cos;
        float sin;
        if(clockWise){
            cos = (float) Math.cos((Math.PI * angle) /180);
            sin = (float) Math.sin((Math.PI * angle) /180);
        } else {
            cos = (float) Math.cos((Math.PI * (360 - angle)) /180);
            sin = (float) Math.sin((Math.PI * (360 - angle)) /180);
        }

        this.endPoint = new PointF(rotationPoint.x + dx*cos - dy*sin, rotationPoint.y + dx*sin - dy*cos);
    }

    @Override
    public PointF calculatePosition(float speed) {
        float angle = (float) ((speed * 180) / (Math.PI * radius));

        if (currentAngle + angle > this.angle) currentAngle = this.angle;
        else currentAngle += angle;
        float dx = startPoint.x - rotationPoint.x;
        float dy = startPoint.y - rotationPoint.y;
        float cos;
        float sin;
        if(clockWise){
            cos = (float) Math.cos((Math.PI * currentAngle) /180);
            sin = (float) Math.sin((Math.PI * currentAngle) /180);
        } else {
            cos = (float) Math.cos((Math.PI * (360 - currentAngle)) /180);
            sin = (float) Math.sin((Math.PI * (360 - currentAngle)) /180);
        }

        currentPoint.x = rotationPoint.x + dx*cos - dy*sin;
        currentPoint.y = rotationPoint.y + dx*sin - dy*cos;

        return currentPoint;
    }

    @Override
    public boolean isEnd() {
        return (currentAngle == angle);
    }

    @Override
    public PointF getStartPoint() {
        return startPoint;
    }

    @Override
    public PointF getEndPoint() {
        return endPoint;
    }

}