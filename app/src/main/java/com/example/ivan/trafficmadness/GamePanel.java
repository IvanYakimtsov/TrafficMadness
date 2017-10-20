package com.example.ivan.trafficmadness;

/**
 * Created by Ivan on 18.10.2017.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Thread gameThread;
    private GameManager gameManager;
    private GameActivity context;

    public GamePanel(GameActivity context) {
        super(context);
        this.context = context;

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        this.gameManager = new GameManager(getHolder(), this);
        gameThread = new Thread(this.gameManager);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                gameManager.setRunning(false);
                gameThread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

        Log.d("check","check");

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //we can safely start the game loop
        gameManager.setRunning(true);
        gameThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameManager.onTouch(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        List<GameObject> gameObjects = gameManager.getGameObjects();
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(canvas);
        }
    }

    public GameActivity getGameActivity() {
        return context;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

}