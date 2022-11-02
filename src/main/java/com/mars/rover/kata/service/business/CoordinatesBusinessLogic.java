package com.mars.rover.kata.service.business;

import com.mars.rover.kata.entity.Coordinates;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mars.rover.kata.service.ICoordinatesService;

@Component
@RequiredArgsConstructor
public class CoordinatesBusinessLogic {

    @Autowired
    private ICoordinatesService coordinatesService;

    /**
     * moves the rover one step forward
     *
     * @return
     */
    public boolean moveRoverForward(Coordinates coordinates) {
        return coordinatesService.move(coordinates, coordinates.getDirection());
    }

    /**
     * moves the rover one step backward
     *
     * @return
     */
    public boolean moveRoverBackward(Coordinates coordinates) {
        return coordinatesService.move(coordinates, coordinates.getDirection().getBackwardDirection());
    }

    /**
     * changes the rover direction to the left
     */
    public void changeDirectionToLeft(Coordinates coordinates) {
        coordinatesService.changeDirection(coordinates, -1);
    }

    /**
     * changes the rover direction to the right
     */
    public void changeDirectionToRight(Coordinates coordinates) {
        coordinatesService.changeDirection(coordinates,1);
    }
}
