package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Ivan on 18.10.2017.
 */

public class CommonCar implements Movable {

    private Point position;
    private Navigator navigator;
    private float height;
    private float width;


    private float velocity;
    private float currentSpeed;
    private float averageSpeed;
    private Bitmap texture;

    private float relativeHeight;
    private float relativeWidth;
    private Matrix matrix;


    //  private boolean isMoving = true;

    public CommonCar(Navigator navigator, float relativeWidth, float relativeHeight, Bitmap texture) {

        this.width = 6 * relativeWidth;
        this.height = 8 * relativeHeight;

        this.relativeHeight = relativeHeight;
        this.relativeWidth = relativeWidth;
        averageSpeed = (float) (0.5 * relativeWidth);
        currentSpeed = (float) (0.3 * relativeWidth);
        velocity = (float) (0.01 * relativeWidth);
        adjustTexture(texture);

        this.position = navigator.getPosition(0);
        this.navigator = navigator;
    }

    private void adjustTexture(Bitmap texture) {
        int width = texture.getWidth();
        int height = texture.getHeight();
        float scaleWidth = this.width / width;
        float scaleHeight = this.height / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix scaleMatrix = new Matrix();
        // RESIZE THE BIT MAP
        scaleMatrix.postScale(scaleWidth, scaleHeight);
        scaleMatrix.postRotate(90);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                texture, 0, 0, width, height, scaleMatrix, true);

        this.width = resizedBitmap.getWidth();
        this.height = resizedBitmap.getHeight();
        this.texture = resizedBitmap;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawBitmap(texture, matrix, paint);

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

        position = navigator.getPosition(currentSpeed);
        updateTexturePosition(0);
    }

    private void updateTexturePosition(double angle){
        this.matrix = new Matrix();
        this.matrix.postRotate((float) angle);
        this.matrix.postTranslate(position.x,position.y);
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
    public Point getPosition() {
        return position;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getWidth() {
        return width;
    }
}
