package com.example.ivan.trafficmadness;

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

//        List<Point> points = new ArrayList<>();
//        points.add(new Point(6,50));
//        points.add(new Point(50,50));
//        points.add(new Point(70,35));
//        points.add(new Point(75,20));
//        points.add(new Point(70,20));
//        points.add(new Point(0,20));
        float relativeWidth = gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeWidth();
        float relativeHeight = gameManager.getGamePanel().getGameActivity().getGameMetrics().getRelativeHeight();
        List<RouteSegment> routeSegments = new ArrayList<>();
     //   Line firstLine = new Line(new Point(0,40*relativeHeight),new Point(30*relativeWidth,40*relativeHeight));
        Turn firstTurn = new Turn(new Point(40*relativeWidth,40*relativeHeight),7*relativeWidth,360);
//        Line secondLine = new Line(firstTurn,new Point(firstTurn.getEndPoint().x + 20 * relativeWidth,
//                firstTurn.getEndPoint().y));
  //      routeSegments.add(firstLine);

         routeSegments.add(firstTurn);

//        routeSegments.add(secondLine);


        gameObjects.add(new CommonCar(new Navigator(routeSegments)
                ,relativeWidth
                ,relativeHeight
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
