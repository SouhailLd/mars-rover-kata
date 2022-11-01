package com.mars.rover.kata.service;

import com.mars.rover.kata.entity.Coordinates;

public interface IRoverService {
    /**
     * handles list of commands received by the rover
     *
     * @param coordinates
     * @param commands
     * @throws IllegalArgumentException
     */
    public void receiveCommands(Coordinates coordinates, String commands) throws IllegalArgumentException;

    /**
     * handles and treats a single command received by the rover
     *
     * @param coordinates
     * @param command
     * @return
     * @throws IllegalArgumentException
     */
    public boolean receiveSingleCommand(Coordinates coordinates, char command) throws IllegalArgumentException;
}
