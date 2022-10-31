package service.impl;

import entity.Point;
import org.springframework.stereotype.Service;
import service.IPointService;

@Service
public class Pointservice implements IPointService {

    @Override
    public int getForwardLocation(Point point) {
        return (point.getLocation() + 1) % (point.getMaxLocation() + 1);
    }

    @Override
    public int getBackwardLocation(Point point) {
        if (point.getLocation() > 0){
            return point.getLocation() - 1;
        } else{
            return point.getMaxLocation();
        }
    }
}
