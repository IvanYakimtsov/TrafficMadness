package com.example.ivan.trafficmadness;

import android.view.MotionEvent;

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

//        List<Point> points = new ArrayList<>();
//        points.add(new Point(6,50));
//        points.add(new Point(50,50));
//        points.add(new Point(70,35));
//        points.add(new Point(75,20));
//        points.add(new Point(70,20));
//        points.add(new Point(0,20));
        List<RouteSegment> routeSegments = new ArrayList<>();
        routeSegments.add(new Line(new Point(0,100),new Point(500,300)));
        routeSegments.add(new Line(new Point(500,300),new Point(500,500)));
        routeSegments.add(new Line(new Point(500,500),new Point(100,500)));


        gameObjects.add(new CommonCar(new Navigator(routeSegments)
                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeWidth()
                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeHeight()
                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getCommonCar()));
//        gameObjects.add(new CommonCar(2, 70
//                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeWidth()
//                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeHeight()
//                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getCommonCar()));
//        gameObjects.add(new CommonCar(2, 30
//                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeWidth()
//                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeHeight()
//                ,gameManager.getGamePanel().getGameActivity().getGameMetrics().getCommonCar()));
    }

    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    public void onTouch(MotionEvent event) {
        Movable car = findCar(event.getX(), event.getY());
        if (car != null) car.changeMovingStatus();
    }

    private Movable findCar(float x, float y) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Movable) {
                if (x >= ((Movable) gameObject).getPosition().x
                        && x <= ((Movable) gameObject).getPosition().x + ((Movable) gameObject).getWidth()
                        && y >= ((Movable) gameObject).getPosition().y
                        && y <= ((Movable) gameObject).getPosition().x + ((Movable) gameObject).getHeight()) {
                    return (Movable) gameObject;
                }
            }
        }
        return null;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
