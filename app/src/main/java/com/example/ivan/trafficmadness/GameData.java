package com.example.ivan.trafficmadness;

import android.graphics.PointF;
import android.util.Log;
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

        float relativeWidth = gameManager.getGamePanel().getGameActivity().getRelativeWidth();
        float relativeHeight = gameManager.getGamePanel().getGameActivity().getRelativeHeight();
        List<RouteSegment> routeSegments = new ArrayList<>();
        Turn firstTurn = new Turn(new PointF(30 * relativeWidth, 40 * relativeHeight), 10 * relativeWidth, -270, -360);
        Line firstLine = new Line(new PointF(0, firstTurn.calculatePosition(0).y),
                firstTurn.calculatePosition(0));
        Line secondLine = new Line(firstTurn.getEndPoint(),
                new PointF(firstTurn.getEndPoint().x, firstTurn.getEndPoint().y - 20 * relativeHeight));
        routeSegments.add(firstLine);

        routeSegments.add(firstTurn);

        routeSegments.add(secondLine);
//        Line firstLine = new Line(new PointF(0, firstTurn.calculatePosition(0).y),
//                firstTurn.calculatePosition(0));
//        Line secondLine = new Line(firstTurn.getEndPoint(),
//                new PointF(firstTurn.getEndPoint().x, firstTurn.getEndPoint().y - 20 * relativeHeight));
       // routeSegments.add(firstLine);


     //   routeSegments.add(secondLine);


        gameObjects.add(new CommonCar(new Navigator(routeSegments)
                , relativeWidth
                , relativeHeight
                , gameManager.getGamePanel().getGameActivity().getCommonCar()));
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
