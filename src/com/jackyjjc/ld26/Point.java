package com.jackyjjc.ld26;

import java.util.HashMap;
import java.util.Map;

/**
 * A data class to represent 2d points and each point is a singleton object
 * to minimize memory usage
 *
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hash(x, y);
    }

    private static final Map<Integer, Point> pointMap = new HashMap<Integer, Point>();

    public static Point createPoint(int x, int y) {

        int hashValue = hash(x, y);
        Point point;

        if(pointMap.containsKey(hashValue)) {
            point = pointMap.get(hashValue);
        } else {
            point = new Point(x, y);
            pointMap.put(point.hashCode(), point);
        }

        return point;
    }

    private static int hash(int x, int y) {
        int result = x;
        result = 179426239 * result + y;
        return result;
    }
}