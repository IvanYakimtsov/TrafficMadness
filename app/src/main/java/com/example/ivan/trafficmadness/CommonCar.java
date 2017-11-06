package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Ivan on 18.10.2017.
 */

public class CommonCar implements Movable {

  //  private PointF position;
    private Navigator navigator;


    private float velocity;
    private float currentSpeed;
    private float averageSpeed;
    private Bitmap texture;

    private float relativeHeight;
    private float relativeWidth;
    private Matrix matrix;

    private RectF bounds;
   // private RectF newBound;


    public CommonCar(Navigator navigator, float relativeWidth, float relativeHeight, Bitmap texture) {

        this.relativeHeight = relativeHeight;
        this.relativeWidth = relativeWidth;
        averageSpeed = (float) (0.4 * relativeWidth);
        currentSpeed = (float) (0.1 * relativeWidth);
        velocity = (float) (0.01 * relativeWidth);
        adjustTexture(texture, 6 * relativeWidth, 6 * relativeWidth);

   //     this.position = navigator.getPosition(0);
        this.navigator = navigator;
        bounds = new RectF(navigator.getCurrentPoint().x,navigator.getCurrentPoint().y,
                navigator.getCurrentPoint().x+this.texture.getWidth(),
                navigator.getCurrentPoint().y+this.texture.getHeight());
  //      newBound = new RectF();
    }

    private void adjustTexture(Bitmap texture, float newWidth, float newHigh) {
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHigh / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix scaleMatrix = new Matrix();
        // RESIZE THE BIT MAP
        scaleMatrix.postScale(scaleWidth, scaleHeight);
        scaleMatrix.postRotate(90);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                texture, 0, 0, width, height, scaleMatrix, true);

        this.texture = resizedBitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(bounds,paint);
        canvas.drawBitmap(texture, matrix, paint);

     //   canvas.drawCircle(navigator.getCurrentPoint().x,navigator.getCurrentPoint().y,20,paint);

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

        navigator.getPosition(currentSpeed);
        updateTexturePosition(navigator.getAngle());
    }

    private void updateTexturePosition(double angle){
        matrix = new Matrix();
        matrix.postRotate((float) angle);
        matrix.postTranslate(navigator.getCurrentPoint().x,navigator.getCurrentPoint().y);
        bounds.set(navigator.getCurrentPoint().x, (float) (navigator.getCurrentPoint().y  + (1.0/3)*this.texture.getHeight()),
                navigator.getCurrentPoint().x+this.texture.getWidth(),
                (float) (navigator.getCurrentPoint().y+this.texture.getHeight() - (1.0/3)*this.texture.getHeight()));
      //  Log.d("check","high " + texture.getHeight());
        Matrix tmp = new Matrix();
        tmp.postRotate((float) angle,navigator.getCurrentPoint().x,navigator.getCurrentPoint().y);
        //tmp.postScale(1,1);
        tmp.mapRect(bounds);
       // matrix.mapRect(bounds);
    }



    @Override
    public void changeMovingStatus() {
       // Log.d("check", "changeMovingStatus");
        if (currentSpeed > 0) {
            velocity = (float) (-0.01 * relativeWidth);
        } else {
            velocity = (float) (0.01 * relativeWidth);
        }
    }

    @Override
    public PointF getPosition() {
        return navigator.getCurrentPoint();
    }

    @Override
    public RectF getBounds() {
        return bounds;
    }
}
