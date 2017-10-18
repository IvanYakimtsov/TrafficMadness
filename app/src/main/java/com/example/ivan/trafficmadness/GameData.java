package com.example.ivan.trafficmadness;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 18.10.2017.
 */

class GameData {
    GameManager gameManager;
    List<GameObject> gameObjects = new ArrayList<>();
    public GameData(GameManager gameManager) {
        this.gameManager = gameManager;
        gameObjects.add(new CommonCar());
        gameObjects.add(new CommonCar());
        gameObjects.add(new CommonCar());
    }

    public void update() {
        for (GameObject gameObject:gameObjects){
            gameObject.update();
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
