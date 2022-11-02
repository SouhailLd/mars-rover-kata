package com.mars.rover.kata;

import com.mars.rover.kata.entity.Coordinates;
import com.mars.rover.kata.entity.Obstacle;
import com.mars.rover.kata.entity.Point;
import com.mars.rover.kata.entity.Rover;
import com.mars.rover.kata.enums.DirectionEnum;
import com.mars.rover.kata.service.business.CoordinatesBusinessLogic;
import com.mars.rover.kata.service.impl.CoordinatesService;
import com.mars.rover.kata.service.impl.Pointservice;
import com.mars.rover.kata.service.impl.RoverService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RoverServiceTest {

    private Rover rover;
    private Coordinates coordinates;
    private DirectionEnum direction;
    private Point x;
    private Point y;
    private List<Obstacle> obstacles;

    @InjectMocks
    private RoverService roverService;
    @InjectMocks
    private CoordinatesBusinessLogic coordinatesBusinessLogic;
    @InjectMocks
    private CoordinatesService coordinatesService;
    @InjectMocks
    private Pointservice pointService;

    @Before
    public void SetUp() {
        direction = DirectionEnum.NORTH;
        x = new Point(3, 100);
        y = new Point(1, 100);
        obstacles = new ArrayList<Obstacle>();
        coordinates = Coordinates.builder()
                .x(x)
                .y(y)
                .direction(direction)
                .obstacles(obstacles).build();
        rover = new Rover(coordinates);
        // Inject mock into the private field
        ReflectionTestUtils.setField(roverService, "coordinatesBusinessLogic", coordinatesBusinessLogic);
        ReflectionTestUtils.setField(coordinatesBusinessLogic, "coordinatesService", coordinatesService);
        ReflectionTestUtils.setField(coordinatesService, "pointService", pointService);
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws IllegalArgumentException {
        Point expectedPosition = new Point(y.getLocation() + 1, y.getMaxLocation());
        roverService.receiveSingleCommand(rover.getCoordinates(), 'F');
        Assert.assertTrue(rover.getCoordinates().getY().equals(expectedPosition));
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws IllegalArgumentException {
        Point expectedPosition = new Point(y.getLocation() - 1, y.getMaxLocation());
        roverService.receiveSingleCommand(rover.getCoordinates(), 'B');
        Assert.assertTrue(rover.getCoordinates().getY().equals(expectedPosition));
    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws IllegalArgumentException {
        roverService.receiveSingleCommand(rover.getCoordinates(), 'L');
        Assert.assertTrue(rover.getCoordinates().getDirection().equals(DirectionEnum.WEST));
    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws IllegalArgumentException {
        roverService.receiveSingleCommand(rover.getCoordinates(), 'R');
        Assert.assertTrue(rover.getCoordinates().getDirection().equals(DirectionEnum.EAST));
    }

    @Test(expected = IllegalArgumentException.class)
    public void receiveSingleCommandShouldThrowIllegalArgumentExceptionWhenCommandIsUnknown() throws IllegalArgumentException {
        roverService.receiveSingleCommand(rover.getCoordinates(), 'X');
    }

    @Test
    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws IllegalArgumentException {
        Point expectedPosition = new Point(x.getLocation() - 2, x.getMaxLocation());
        roverService.receiveListOfCommands(rover.getCoordinates(), "RBBR");
        Assert.assertTrue(rover.getCoordinates().getX().equals(expectedPosition));
        Assert.assertTrue(rover.getCoordinates().getDirection().equals(DirectionEnum.SOUTH));
    }

    @Test
    public void receiveCommandShouldSwitchRoverFromOneEdgeOfThePlanetToAnother() throws IllegalArgumentException {
        Point expectedPosition = new Point(y.getMaxLocation(), y.getMaxLocation());
        roverService.receiveListOfCommands(rover.getCoordinates(), "LLFF");
        Assert.assertTrue(rover.getCoordinates().getY().equals(expectedPosition));
        Assert.assertTrue(rover.getCoordinates().getDirection().equals(DirectionEnum.SOUTH));
    }

    @Test
    public void receiveCommandsShouldStopWhenObstacleIsFound() throws IllegalArgumentException {
        Point expectedPosition = new Point(x.getLocation(), x.getMaxLocation());
        Point aimedPosition = new Point(x.getLocation() + 1, x.getMaxLocation());
        rover.getCoordinates().setObstacles(Arrays.asList(new Obstacle(aimedPosition.getLocation(), y.getLocation())));
        roverService.receiveListOfCommands(rover.getCoordinates(), "RF");
        Assert.assertTrue(rover.getCoordinates().getX().equals(expectedPosition));
        Assert.assertTrue(rover.getCoordinates().getDirection().equals(DirectionEnum.EAST));
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        Point aimedPosition = new Point(x.getLocation() + 1, x.getMaxLocation());
        rover.getCoordinates().setObstacles(Arrays.asList(new Obstacle(aimedPosition.getLocation(), y.getLocation())));
        roverService.receiveListOfCommands(rover.getCoordinates(), "RF");
        Assert.assertTrue(roverService.getCurrentRoverPosition(rover.getCoordinates()).endsWith("Yes"));
    }
}
