package com.mars.rover.kata.service.business;

import com.mars.rover.kata.entity.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mars.rover.kata.service.ICoordinatesService;

@Component
public class CoordinatesBusinessLogic {

    @Autowired
    private ICoordinatesService coordinatesService;

    /**
     * moves the rover one step forward
     *
     * @return
     */
    public boolean moveForward(Coordinates coordinates) {
        return coordinatesService.move(coordinates, coordinates.getDirection());
    }

    /**
     * moves the rover one step backward
     *
     * @return
     */
    public boolean moveBackward(Coordinates coordinates) {
        return coordinatesService.move(coordinates, coordinates.getDirection().getBackwardDirection());
    }

    /**
     * changes the rover direction to the left
     */
    public void changeDirectionLeft(Coordinates coordinates) {
        coordinatesService.changeDirection(coordinates, -1);
    }

    /**
     * changes the rover direction to the right
     */
    public void changeDirectionRight(Coordinates coordinates) {
        coordinatesService.changeDirection(coordinates,1);
    }
}
