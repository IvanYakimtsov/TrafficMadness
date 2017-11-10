package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 10.11.2017.
 */

public class Level implements Serializable {
    private Bitmap background;
    private float gameTime;
    private int score;
    private List<GameObject> gameObjects;
    private Map< Float, List<Movable> > carSpawner;

    public List<Movable> spawnCar(float time){
        return carSpawner.get(time);
    }


    public Bitmap getBackground() {
        return background;
    }

    public void setBackground(Bitmap background) {
        this.background = background;
    }

    public float getGameTime() {
        return gameTime;
    }

    public void setGameTime(float gameTime) {
        this.gameTime = gameTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Map<Float, List<Movable>> getCarSpawner() {
        return carSpawner;
    }

    public void setCarSpawner(Map<Float, List<Movable>> carSpawner) {
        this.carSpawner = carSpawner;
    }
}
