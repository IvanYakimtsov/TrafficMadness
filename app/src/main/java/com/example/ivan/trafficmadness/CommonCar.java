package com.example.ivan.trafficmadness;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Ivan on 18.10.2017.
 */

public class CommonCar implements Car {
    private int x = 0;
    private int y = 250;
    private int high = 15;
    private int width = 35;
    @Override
    public void draw(Canvas canvas) {
        Paint paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawRect(x,y,x+width,y+high,paint2);
    }

    @Override
    public void update() {
        if(x < 400) x += 3;
        else x = 0;
    }
}
