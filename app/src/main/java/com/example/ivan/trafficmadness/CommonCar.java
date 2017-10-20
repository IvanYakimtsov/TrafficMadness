package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.List;

/**
 * Created by Ivan on 18.10.2017.
 */

public class CommonCar implements Car {
    private float x;
    private float y;
    private float high;
    private float width;
    private float relativeHigh;
    private float relativeWidth;

    private double velocity;
    private double currentSpeed;
    private double averageSpeed;
    private Matrix matrix;

    private List<Point> points;
    private int currentPointIndex = 0;

    private Bitmap texture;

    //  private boolean isMoving = true;

    public CommonCar(float x, float y, float relativeWidth, float relativeHigh, Bitmap texture, List<Point> points) {
        this.x = x * relativeWidth;
        this.y = y * relativeHigh;
        this.points = points;
        this.width = 8 * relativeWidth;
        this.high = 8 * relativeHigh;

        this.relativeHigh = relativeHigh;
        this.relativeWidth = relativeWidth;
        averageSpeed = (float) (0.5 * relativeWidth);
        currentSpeed = (float) (0.3 * relativeWidth);
        velocity = (float) (0.01 * relativeWidth);
        adjustTexture(texture);
    }

    private void adjustTexture(Bitmap texture) {
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scaleWidth = this.width / width;
        float scaleHeight = this.high / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix scaleMatrix = new Matrix();
        // RESIZE THE BIT MAP
        scaleMatrix.postScale(scaleWidth, scaleHeight);
        scaleMatrix.postRotate(90);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                texture, 0, 0, width, height, scaleMatrix, true);

        this.width = resizedBitmap.getWidth();
        this.high = resizedBitmap.getHeight();
        this.texture = resizedBitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(x,y,x+width,y+high,paint);
        canvas.drawBitmap(texture, matrix, paint);
        canvas.drawCircle(points.get(currentPointIndex).x * relativeWidth
                ,points.get(currentPointIndex).y *relativeHigh,25,paint);

    }

    @Override
    public void update() {
        if (currentSpeed < averageSpeed) {
            currentSpeed += velocity;
        }
        if (velocity < 0 && currentSpeed != 0) {
            currentSpeed += velocity;
            if (currentSpeed <= 0) currentSpeed = 0;
        }
     //   x += currentSpeed;
        Point point = points.get(currentPointIndex);
        double tgX = ((point.y * relativeHigh) - y) / ((point.x*relativeWidth) - x);
        double X = Math.atan(tgX);
        Log.d("check","angle " + X);
        double ySpeed = currentSpeed * Math.sin(X);
        double xSpeed = currentSpeed * Math.cos(X);
      //  Log.d("check","currentSpeed " + currentSpeed);
      //  Log.d("check","angle " + X + " xSpeed "+ xSpeed + " ySpeed "+ ySpeed);
        x += xSpeed;
        y += ySpeed;
        setTexturePosition(x,y,X);
        checkPoint();

    }

    private void setTexturePosition(float x, float y, double angle){
        this.matrix = new Matrix();
        this.matrix.postRotate((float) angle);
        this.matrix.postTranslate(x,y);
    }

    private void checkPoint() {
       // Log.d("check","currentPoint " + currentPointIndex);
        double radius = width;
        if (Math.abs(x - points.get(currentPointIndex).x)<=radius
                && Math.abs(y - points.get(currentPointIndex).x)<=radius ) {
            if (currentPointIndex < points.size() - 1) {
                currentPointIndex++;
            } else  velocity = (float) (-0.01 * relativeWidth);;
        }
    }

    @Override
    public void changeMovingStatus() {
        if (currentSpeed > 0) {
            velocity = (float) (-0.01 * relativeWidth);
        } else {
            velocity = (float) (0.01 * relativeWidth);
        }
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getHigh() {
        return high;
    }

    @Override
    public float getWidth() {
        return width;
    }
}
