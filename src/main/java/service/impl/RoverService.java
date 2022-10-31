package service.impl;

import entity.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IRoverService;
import service.business.CoordinatesBusinessLogic;

@Service
public class RoverService implements IRoverService {

    @Autowired
    private CoordinatesBusinessLogic coordinatesBusinessLogic;

    @Override
    public void receiveCommands(Coordinates coordinates, String commands) throws IllegalArgumentException {
        for (char command : commands.toCharArray()) {
            if (!receiveSingleCommand(coordinates, command)) {
                break;
            }
        }
    }

    @Override
    public boolean receiveSingleCommand(Coordinates coordinates, char command) throws IllegalArgumentException {
        switch(Character.toUpperCase(command)) {
            case 'F':
                return coordinatesBusinessLogic.moveForward(coordinates);
            case 'B':
                return coordinatesBusinessLogic.moveBackward(coordinates);
            case 'L':
                coordinatesBusinessLogic.changeDirectionLeft(coordinates);
                return true;
            case 'R':
                coordinatesBusinessLogic.changeDirectionRight(coordinates);
                return true;
            default:
                throw new IllegalArgumentException("Command " + command + " is unknown.");
        }
    }
}
