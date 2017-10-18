package com.example.ivan.trafficmadness;

/**
 * Created by Ivan on 18.10.2017.
 */

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.List;

public class GameManager implements Runnable
{
    private int FPS = 30;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private Canvas canvas;
    private GameData gameData;
    private double totalTime;

    public GameManager(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.gameData = new GameData(this);
    }
    @Override
    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;

        long targetTime = 1000/FPS;

        while(running) {
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
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }




            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                Thread.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;

        }
    }
    public void setRunning(boolean b)
    {
        running=b;
    }

    public List<GameObject> getGameObjects() {
        return gameData.gameObjects;
    }
}