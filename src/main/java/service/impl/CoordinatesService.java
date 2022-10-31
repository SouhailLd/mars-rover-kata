package service.impl;

import entity.Coordinates;
import entity.Point;
import enums.DirectionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import service.ICoordinatesService;
import service.IPointService;

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
        if (!hasObstacle(coordinates)) {
            x.setLocation(xLocation);
            y.setLocation(yLocation);
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
     *
     * @param coordinates
     * @return
     */
    private boolean hasObstacle(Coordinates coordinates) {
        return coordinates
                .getObstacles()
                .stream()
                .anyMatch(obstacle ->
                        obstacle.getX() == coordinates.getX().getLocation()
                                && obstacle.getY() == coordinates.getY().getLocation());
    }
}
