package com.example.ivan.trafficmadness;

/**
 * Created by Ivan on 18.10.2017.
 */

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.List;

public class GameManager implements Runnable {
    private int FPS = 60;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private boolean pause = false;
    private Canvas canvas;
    private GameData gameData;
    private double totalTime;


    public GameManager(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.gameData = new GameData(this);
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;

        long targetTime = 1000 / FPS;

        while (running) {
            if (!pause) {
                startTime = System.nanoTime();
                canvas = null;

                //try locking the canvas for pixel editing
                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        this.gameData.update();
                        this.gamePanel.draw(canvas);
                    }
                } catch (Exception e) {
                } finally {
                    if (canvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


                timeMillis = (System.nanoTime() - startTime) / 1000000;
                waitTime = targetTime - timeMillis;

                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {
                }

                totalTime += System.nanoTime() - startTime;

            } else Thread.yield();
        }
    }

    public void setRunning(boolean b) {
        running = b;
    }

    public void onTouch(MotionEvent event) {
        gameData.onTouch(event);
    }

    public List<GameObject> getGameObjects() {
        return gameData.gameObjects;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}