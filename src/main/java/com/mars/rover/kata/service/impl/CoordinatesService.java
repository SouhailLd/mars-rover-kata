package com.mars.rover.kata.service.impl;

import com.mars.rover.kata.entity.Coordinates;
import com.mars.rover.kata.enums.DirectionEnum;
import com.mars.rover.kata.service.ICoordinatesService;
import com.mars.rover.kata.service.IPointService;
import com.mars.rover.kata.entity.Obstacle;
import com.mars.rover.kata.entity.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesService implements ICoordinatesService {

    @Autowired
    public IPointService pointService;

    @Override
    public boolean move(Coordinates coordinates, DirectionEnum directionValue) {
        Point x = coordinates.getX();
        Point y = coordinates.getY();
        int xLocation = x.getLocation();
        int yLocation = y.getLocation();
        switch (directionValue) {
            case NORTH:
                yLocation = pointService.getForwardLocation(y);
                break;
            case EAST:
                xLocation = pointService.getForwardLocation(x);
                break;
            case SOUTH:
                yLocation = pointService.getBackwardLocation(y);
                break;
            case WEST:
                xLocation = pointService.getBackwardLocation(x);
                break;
        }
        if (!hasObstacle(coordinates.getObstacles(), xLocation, yLocation)) {
            coordinates.getX().setLocation(xLocation);
            coordinates.getY().setLocation(yLocation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changeDirection(Coordinates coordinates, int directionStep) {
        DirectionEnum directionValue = coordinates.getDirection();
        int directions = DirectionEnum.values().length;
        int index = (directions + directionValue.getValue() + directionStep) % directions;
        coordinates.setDirection(DirectionEnum.values()[index]);
    }

    /**
     * checks if current position has obstacle
     *
     * @param obstacleList
     * @param xLocation
     * @param yLocation
     * @return
     */
    private boolean hasObstacle(List<Obstacle> obstacleList, int xLocation, int yLocation) {
        return obstacleList
                .stream()
                .anyMatch(obstacle ->
                        obstacle.getX() == xLocation
                                && obstacle.getY() == yLocation);
    }
}
