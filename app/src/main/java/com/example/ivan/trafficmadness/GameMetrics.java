package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;

/**
 * Created by Ivan on 18.10.2017.
 */

public class GameMetrics {
    private float relativeHeight;
    private float relativeWidth;

    public Bitmap commonCar;

    public GameMetrics(float relativeWidth, float relativeHeight) {
        this.relativeWidth = relativeWidth;
        this.relativeHeight = relativeHeight;
    }

    public void setRelativeHeight(float relativeHeight) {
        this.relativeHeight = relativeHeight;
    }

    public void setRelativeWidth(float relativeWidth) {
        this.relativeWidth = relativeWidth;
    }

    public float getRelativeHeight() {
        return relativeHeight;
    }

    public float getRelativeWidth() {
        return relativeWidth;
    }

    public Bitmap getCommonCar() {
        return commonCar;
    }

    public void setCommonCar(Bitmap commonCar) {
        this.commonCar = commonCar;
    }
}
