package service;

import entity.Coordinates;

public interface IRoverService {
    /**
     *
     * @param coordinates
     * @param commands
     * @throws IllegalArgumentException
     */
    public void receiveCommands(Coordinates coordinates, String commands) throws IllegalArgumentException;

    /**
     *
     * @param coordinates
     * @param command
     * @return
     * @throws IllegalArgumentException
     */
    public boolean receiveSingleCommand(Coordinates coordinates, char command) throws IllegalArgumentException;
}
