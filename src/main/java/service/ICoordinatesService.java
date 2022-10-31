package service;

import entity.Coordinates;
import enums.DirectionEnum;

public interface ICoordinatesService {
    /**
     *
     * @param coordinates
     * @param directionValue
     * @return
     */
    public boolean move(Coordinates coordinates, DirectionEnum directionValue);

    /**
     *
     * @param coordinates
     * @param directionStep
     */
    public void changeDirection(Coordinates coordinates, int directionStep);
}
