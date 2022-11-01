package com.mars.rover.kata.service.impl;

import com.mars.rover.kata.entity.Point;
import org.springframework.stereotype.Service;
import com.mars.rover.kata.service.IPointService;

@Service
public class Pointservice implements IPointService {

    @Override
    public int getForwardLocation(Point point) {
        if (point.getLocation() == point.getMaxLocation())
            return 0;
        else
            return point.getLocation() + 1;
    }

    @Override
    public int getBackwardLocation(Point point) {
        if (point.getLocation() > 0)
            return point.getLocation() - 1;
        else
            return point.getMaxLocation();
    }
}
