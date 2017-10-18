package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;

/**
 * Created by Ivan on 18.10.2017.
 */

public class GameMetrics {
    private float relativeHigh;
    private float relativeWidth;

    public Bitmap commonCar;

    public GameMetrics(float relativeWidth, float relativeHigh) {
        this.relativeWidth = relativeWidth;
        this.relativeHigh = relativeHigh;
    }

    public void setRelativeHigh(float relativeHigh) {
        this.relativeHigh = relativeHigh;
    }

    public void setRelativeWidth(float relativeWidth) {
        this.relativeWidth = relativeWidth;
    }

    public float getRelativeHigh() {
        return relativeHigh;
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
