package com.example.ivan.trafficmadness;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 18.10.2017.
 */

class GameData {
    private GameManager gameManager;
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<Movable> movables = new ArrayList<>();

    public GameData(GameManager gameManager) {
        this.gameManager = gameManager;

        float relativeWidth = gameManager.getGamePanel().getGameActivity().getRelativeWidth();
        float relativeHeight = gameManager.getGamePanel().getGameActivity().getRelativeHeight();
        List<RouteSegment> routeSegments = new ArrayList<>();

        Turn firstTurn = new Turn(new PointF(45 * relativeWidth, 50 * relativeHeight), 10 * relativeWidth, -270, -360);

        Line firstLine = new Line(new PointF(0, firstTurn.calculatePosition(0).y),
                firstTurn.calculatePosition(0));

        Line secondLine = new Line(firstTurn.getEndPoint(),
                new PointF(firstTurn.getEndPoint().x, 0));


        routeSegments.add(firstLine);
        routeSegments.add(firstTurn);
        routeSegments.add(secondLine);
        movables.add(new CommonCar(new Navigator(routeSegments)
                , relativeWidth
                , relativeHeight
                , gameManager.getGamePanel().getGameActivity().getCommonCar()));

        routeSegments = new ArrayList<>();
        firstLine = new Line(new PointF(110 * relativeWidth, 40 * relativeHeight), new PointF(0, 40 * relativeHeight));
        routeSegments.add(firstLine);
        movables.add(new CommonCar(new Navigator(routeSegments)
                , relativeWidth
                , relativeHeight
                , gameManager.getGamePanel().getGameActivity().getCommonCar()));
    }

    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
        for (Movable movable : movables) {
            movable.update();
        }
        checkCollision();
    }

    private void checkCollision() {
        for (Movable object1 : movables) {
            for (Movable object2 : movables) {
                if(object1 != object2){
                    if (RectF.intersects(object1.getBounds(),object2.getBounds())) {
//                        Log.d("check", object1.getBounds().left + " object1 left");
//                        Log.d("check", object2.getBounds().left + " object2 left");
//                        Log.d("check", object1.getBounds().right + " object1 right");
//                        Log.d("check", object2.getBounds().right + " object2 right");
                        gameManager.setPause(true);
                    }
                }

            }
        }
    }

    public void onTouch(MotionEvent event) {
        Movable car = findCar(event.getX(), event.getY());
        //  Log.d("check", "onTouch");
        if (car != null) {
            Log.d("check", "hit");
            car.changeMovingStatus();
        }
    }

    private Movable findCar(float x, float y) {
        for (Movable movable : movables) {
            if (movable.getBounds().contains(x, y)) {
                return movable;
            }
        }
        return null;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<Movable> getMovables() {
        return movables;
    }
}
