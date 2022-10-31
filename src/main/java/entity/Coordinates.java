package entity;

import enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coordinates {
    private Point x;
    private Point y;
    private DirectionEnum direction;
    private List<Obstacle> obstacles;
    private boolean foundObstacle = false;

    public String toString() {
        String status = "";
        if (foundObstacle) {
            status = " NOK";
        }
        return getX().getLocation() + " X " + getY().getLocation() + " " + getDirection().getShortName() + status;
    }
}
