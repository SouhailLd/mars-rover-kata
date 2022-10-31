package service;

import entity.Point;

public interface IPointService {
    /**
     *
     * @param point
     * @return
     */
    public int getForwardLocation(Point point);

    /**
     *
     * @param point
     * @return
     */
    public int getBackwardLocation(Point point);
}
