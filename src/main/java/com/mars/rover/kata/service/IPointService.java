package com.mars.rover.kata.service;

import com.mars.rover.kata.entity.Point;

public interface IPointService {
    /**
     * computes forward location based on current point position
     *
     * @param point
     * @return
     */
    public int getForwardLocation(Point point);

    /**
     * computes forward location based on current point position
     *
     * @param point
     * @return
     */
    public int getBackwardLocation(Point point);
}
