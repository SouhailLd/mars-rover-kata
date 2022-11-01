import com.mars.rover.kata.entity.Coordinates;
import com.mars.rover.kata.entity.Obstacle;
import com.mars.rover.kata.entity.Point;
import com.mars.rover.kata.enums.DirectionEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.mars.rover.kata.service.business.CoordinatesBusinessLogic;

import java.util.Arrays;
import java.util.List;

public class MainApplication {

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CoordinatesBusinessLogic coordinatesBusinessLogic =
                appContext.getBean("coordinatesBusinessLogic", CoordinatesBusinessLogic.class);
        DirectionEnum direction = DirectionEnum.NORTH;
        Point x = new Point(1, 99);
        Point y = new Point(99, 99);
        List<Obstacle> obstacles = Arrays.asList(new Obstacle(20, 20), new Obstacle(30, 30));
        Coordinates coordinates = Coordinates.builder()
                .x(x)
                .y(y)
                .direction(direction)
                .obstacles(obstacles).build();
        coordinatesBusinessLogic.moveForward(coordinates);
        System.out.println("X= " +coordinates.getX().getLocation()+ " Y= " +coordinates.getY().getLocation());
        coordinatesBusinessLogic.moveBackward(coordinates);
        System.out.println("X= " +coordinates.getX().getLocation()+ " Y= " +coordinates.getY().getLocation());
        coordinates.setDirection(DirectionEnum.EAST);
        coordinatesBusinessLogic.moveForward(coordinates);
        System.out.println("X= " +coordinates.getX().getLocation()+ " Y= " +coordinates.getY().getLocation());
    }

}
