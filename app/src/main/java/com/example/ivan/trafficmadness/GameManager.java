package com.example.ivan.trafficmadness;

/**
 * Created by Ivan on 18.10.2017.
 */

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.List;

public class GameManager implements Runnable {
    private int FPS = 60;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running = false;
    private boolean pause = true;
   // private boolean isGameOver = false;
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

            } /*else Thread.yield()*/;
        }
    }

    public void gameOver(){
        pause = true;
     //   gameData.setCars();
     //   isGameOver = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(gamePanel.getGameActivity());
        builder.setTitle("You loose!")
                .setMessage("Do you want to try again?")
                .setCancelable(false)
                .setNegativeButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gameData.setCars();
                                pause = false;
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gamePanel.getGameActivity().finish();
                            }
                        });
        builder.create().show();
//        Toast toast = Toast.makeText(gamePanel.getContext(),
//                "Пора покормить кота!", Toast.LENGTH_SHORT);
//        toast.show();
//        AlertDialog alert = builder.create();
//        alert.getCurrentFocus();
//        alert.show();
    }

    public void setRunning(boolean b) {
        running = b;
    }

    public void onTouch(MotionEvent event) {
        gameData.onTouch(event);
    }

    public List<GameObject> getGameObjects() {
        return gameData.getGameObjects();
    }
    public List<Movable> getMovableObjects() {
        return gameData.getMovables();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isRunning(){
        return running;
    }

    public boolean isPause(){
        return pause;
    }

    public GameData getGameData() {
        return gameData;
    }

}