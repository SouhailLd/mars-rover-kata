package com.mars.rover.kata.service;

import com.mars.rover.kata.entity.Coordinates;
import com.mars.rover.kata.enums.DirectionEnum;

public interface ICoordinatesService {
    /**
     * moves the rover according to coordinates input and the direction chosen
     *
     * @param coordinates
     * @param directionValue
     * @return
     */
    public boolean move(Coordinates coordinates, DirectionEnum directionValue);

    /**
     * change the direction of the rover according to current coordinates and the step direction
     *
     * @param coordinates
     * @param directionStep
     */
    public void changeDirection(Coordinates coordinates, int directionStep);
}
