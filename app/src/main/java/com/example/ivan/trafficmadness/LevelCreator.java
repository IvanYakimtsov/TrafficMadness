package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;

/**
 * Created by Ivan on 08.11.2017.
 */

public class LevelCreator {
    private float relativeHeight;
    private float relativeWidth;
    private Bitmap commonCar;
    private Bitmap backGround;

    public Level createLevel(int id){
        return new Level();
    }


    public float getRelativeHeight() {
        return relativeHeight;
    }

    public void setRelativeHeight(float relativeHeight) {
        this.relativeHeight = relativeHeight;
    }

    public float getRelativeWidth() {
        return relativeWidth;
    }

    public void setRelativeWidth(float relativeWidth) {
        this.relativeWidth = relativeWidth;
    }

    public Bitmap getCommonCar() {
        return commonCar;
    }

    public void setCommonCar(Bitmap commonCar) {
        this.commonCar = commonCar;
    }

    public Bitmap getBackGround() {
        return backGround;
    }

    public void setBackground(Bitmap background) {
        this.backGround = background;
    }
}
