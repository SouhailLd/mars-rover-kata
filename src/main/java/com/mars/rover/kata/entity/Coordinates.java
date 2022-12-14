package com.mars.rover.kata.entity;

import com.mars.rover.kata.enums.DirectionEnum;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Coordinates {
    private Point x;
    private Point y;
    private DirectionEnum direction;
    private List<Obstacle> obstacles;
    private boolean foundObstacle = false;

}
