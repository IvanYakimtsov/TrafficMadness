package com.example.ivan.trafficmadness;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 10.11.2017.
 */

@Deprecated
public class FirstLevelData {
    private Bitmap background;
    private float gameTime;
    private int score;
    private List<GameObject> gameObjects;
    private Map< Float, List<Movable> > carSpawner;
    private LevelCreator levelCreator;

    public FirstLevelData(LevelCreator levelCreator) {
        this.levelCreator = levelCreator;
    }


    public Bitmap getBackground() {
        return background;
    }

    public float getGameTime() {
        return gameTime;
    }

    public int getScore() {
        return score;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Map<Float, List<Movable>> getCarSpawner() {
        return carSpawner;
    }

    public LevelCreator getLevelCreator() {
        return levelCreator;
    }
}
