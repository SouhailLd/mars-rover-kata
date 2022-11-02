package com.mars.rover.kata.service.impl;

import com.mars.rover.kata.entity.Coordinates;
import com.mars.rover.kata.service.IRoverService;
import com.mars.rover.kata.service.business.CoordinatesBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoverService implements IRoverService {

    @Autowired
    private CoordinatesBusinessLogic coordinatesBusinessLogic;

    @Override
    public void receiveListOfCommands(Coordinates coordinates, String commands) throws IllegalArgumentException {
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
                return coordinatesBusinessLogic.moveRoverForward(coordinates);
            case 'B':
                return coordinatesBusinessLogic.moveRoverBackward(coordinates);
            case 'L':
                coordinatesBusinessLogic.changeDirectionToLeft(coordinates);
                return true;
            case 'R':
                coordinatesBusinessLogic.changeDirectionToRight(coordinates);
                return true;
            default:
                throw new IllegalArgumentException("Command " + command + " is unknown.");
        }
    }

    @Override
    public String getCurrentRoverPosition(Coordinates coordinates) {
        String status = "";
        if (coordinates.isFoundObstacle()) {
            status = "Yes";
        }
        return "X= " + coordinates.getX().getLocation() + " Y=" + coordinates.getY().getLocation()
                + " Direction = " + coordinates.getDirection().getShortName() + " Has obstacle = " +status;
    }
}
