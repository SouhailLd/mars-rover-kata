package com.mars.rover.kata;

import com.mars.rover.kata.entity.Coordinates;
import com.mars.rover.kata.entity.Obstacle;
import com.mars.rover.kata.entity.Point;
import com.mars.rover.kata.enums.DirectionEnum;
import com.mars.rover.kata.service.business.CoordinatesBusinessLogic;
import com.mars.rover.kata.service.impl.CoordinatesService;
import com.mars.rover.kata.service.impl.Pointservice;
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
public class CoordinatesBusinessLogicTest {

    private DirectionEnum direction;
    private Point x;
    private Point y;
    private List<Obstacle> obstacles;
    private Coordinates coordinates;

    @InjectMocks
    private CoordinatesBusinessLogic coordinatesBusinessLogic;
    @InjectMocks
    private CoordinatesService coordinatesService;
    @InjectMocks
    private Pointservice pointService;

    @Before
    public void SetUp () {
        direction = DirectionEnum.NORTH;
        x = new Point(1, 100);
        y = new Point(1, 100);
        obstacles = new ArrayList<Obstacle>();
        coordinates = Coordinates.builder()
                .x(x)
                .y(y)
                .direction(direction)
                .obstacles(obstacles).build();
        // Inject mock into the private field
        ReflectionTestUtils.setField(coordinatesBusinessLogic, "coordinatesService", coordinatesService);
        ReflectionTestUtils.setField(coordinatesService, "pointService", pointService);
    }

    @Test
    public void moveRoverForwardShouldIncreaseYWhenDirectionIsNorth() {
        Point expectedPosition = new Point(y.getLocation() + 1, y.getMaxLocation());
        coordinates.setDirection(DirectionEnum.NORTH);
        coordinatesBusinessLogic.moveRoverForward(coordinates);
        Assert.assertTrue(coordinates.getY().equals(expectedPosition));
    }

    @Test
    public void moveRoverForwardShouldIncreaseYWhenDirectionIsSouth() {
        Point expectedPosition = new Point(y.getLocation() - 1, y.getMaxLocation());
        coordinates.setDirection(DirectionEnum.SOUTH);
        coordinatesBusinessLogic.moveRoverForward(coordinates);
        Assert.assertTrue(coordinates.getY().equals(expectedPosition));
    }

    @Test
    public void moveRoverForwardShouldDecreaseXWhenDirectionIsWest() {
        Point expectedPosition = new Point(x.getLocation() - 1, x.getMaxLocation());
        coordinates.setDirection(DirectionEnum.WEST);
        coordinatesBusinessLogic.moveRoverForward(coordinates);
        Assert.assertTrue(coordinates.getX().equals(expectedPosition));
    }

    @Test
    public void moveRoverForwardShouldIncreaseYWhenDirectionIsEast() {
        Point expectedPosition = new Point(x.getLocation() + 1, x.getMaxLocation());
        coordinates.setDirection(DirectionEnum.EAST);
        coordinatesBusinessLogic.moveRoverForward(coordinates);
        Assert.assertTrue(coordinates.getX().equals(expectedPosition));
    }

    @Test
    public void moveRoverBackwardShouldDecreaseYWhenDirectionIsNorth() {
        Point expectedPosition = new Point(y.getLocation() - 1, y.getMaxLocation());
        coordinates.setDirection(DirectionEnum.NORTH);
        coordinatesBusinessLogic.moveRoverBackward(coordinates);
        Assert.assertTrue(coordinates.getY().equals(expectedPosition));
    }

    @Test
    public void moveRoverBackwardShouldIncreaseYWhenDirectionIsSouth() {
        Point expectedPosition = new Point(y.getLocation() + 1, y.getMaxLocation());
        coordinates.setDirection(DirectionEnum.SOUTH);
        coordinatesBusinessLogic.moveRoverBackward(coordinates);
        Assert.assertTrue(coordinates.getY().equals(expectedPosition));
    }

    @Test
    public void moveRoverBackwardShouldIncreaseXWhenDirectionIsWest() {
        Point expectedPosition = new Point(x.getLocation() + 1, x.getMaxLocation());
        coordinates.setDirection(DirectionEnum.WEST);
        coordinatesBusinessLogic.moveRoverBackward(coordinates);
        Assert.assertTrue(coordinates.getX().equals(expectedPosition));
    }

    @Test
    public void moveRoverBackwardShouldDecreaseXWhenDirectionIsEast() {
        Point expectedPosition = new Point(x.getLocation() - 1, x.getMaxLocation());
        coordinates.setDirection(DirectionEnum.EAST);
        coordinatesBusinessLogic.moveRoverBackward(coordinates);
        Assert.assertTrue(coordinates.getX().equals(expectedPosition));
    }

    @Test
    public void moveRoverForwardShouldNotChangeLocationsWhenObstacleIsFound() {
        Point expectedPosition = new Point(x.getLocation(), x.getMaxLocation());
        coordinates.setDirection(DirectionEnum.EAST);
        coordinates.setObstacles(Arrays.asList(new Obstacle(x.getLocation() + 1, y.getLocation())));
        coordinatesBusinessLogic.moveRoverForward(coordinates);
        Assert.assertTrue(coordinates.getX().equals(expectedPosition));
    }
}
