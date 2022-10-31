package service.business;

import entity.Coordinates;
import enums.DirectionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ICoordinatesService;

@Component
public class CoordinatesBusinessLogic {

    @Autowired
    private ICoordinatesService coordinatesService;

    /**
     *
     * @return
     */
    public boolean moveForward(Coordinates coordinates) {
        return coordinatesService.move(coordinates, coordinates.getDirection());
    }

    /**
     *
     * @return
     */
    public boolean moveBackward(Coordinates coordinates) {
        return coordinatesService.move(coordinates, coordinates.getDirection().getBackwardDirection());
    }

    /**
     *
     */
    public void changeDirectionLeft(Coordinates coordinates) {
        coordinatesService.changeDirection(coordinates, -1);
    }

    /**
     *
     */
    public void changeDirectionRight(Coordinates coordinates) {
        coordinatesService.changeDirection(coordinates,1);
    }
}
