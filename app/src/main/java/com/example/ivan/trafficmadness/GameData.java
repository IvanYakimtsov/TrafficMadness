package com.example.ivan.trafficmadness;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 18.10.2017.
 */

class GameData {
    private final int INFELICITY = 7;
    GameManager gameManager;
    List<GameObject> gameObjects = new ArrayList<>();

    public GameData(GameManager gameManager) {
        this.gameManager = gameManager;
        gameObjects.add(new CommonCar(2, 50
                ,gameManager.getGameMetrics().getRelativeWidth(), gameManager.getGameMetrics().getRelativeHigh()
                ,gameManager.getGameMetrics().getCommonCar()));
        gameObjects.add(new CommonCar(2, 70,
                gameManager.getGameMetrics().getRelativeWidth(), gameManager.getGameMetrics().getRelativeHigh()
                ,gameManager.getGameMetrics().getCommonCar()));
        gameObjects.add(new CommonCar(2, 30,
                gameManager.getGameMetrics().getRelativeWidth(), gameManager.getGameMetrics().getRelativeHigh()
                ,gameManager.getGameMetrics().getCommonCar()));
    }

    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    public void onTouch(MotionEvent event) {
        Car car = findCar(event.getX(), event.getY());
        if (car != null) car.changeMovingStatus();
    }

    private Car findCar(float x, float y) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Car) {
                if (x >= ((Car) gameObject).getX() - INFELICITY
                        && x <= ((Car) gameObject).getX() + ((Car) gameObject).getWidth() + INFELICITY
                        && y >= ((Car) gameObject).getY() - INFELICITY
                        && y <= ((Car) gameObject).getY() + ((Car) gameObject).getHigh() + INFELICITY) {
                    return (Car) gameObject;
                }
            }
        }
        return null;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
