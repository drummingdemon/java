/*
 * Given points of a plane it calculates the farthest point from the origin and the nearest to the first point
 */
package warmup2_1;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author drummer
 */
public class Warmup2_1 {
    
    static List<Point> points = new ArrayList<>();
    
    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            System.out.println("Invalid number of coordinates for points!");
            System.exit(-1);
        }
        readPointsFromInput(args);
    }
    
    public static void readPointsFromInput(String[] input) {
        for (int i = 0; i < input.length ; i=i+2) {
            points.add(new Point(Double.parseDouble(input[i]), Double.parseDouble(input[i+1])));
        }
        farthestFromOrigin();
        closestToFirst();
    }
    
    public static void farthestFromOrigin() {
        Point origin = new Point(0.0,0.0);
        double farthestDistance = 0;
        Point farthestPoint = origin;
        for (Point point : points) {
            double currentDistance = distanceBetweenPoints(origin, point);
            if (currentDistance > farthestDistance) {
                farthestDistance = currentDistance;
                farthestPoint = point;
            }
        }
        System.out.println("Farthest point from origin is (" + farthestPoint.x + "," + farthestPoint.y + ") at a distance of " + farthestDistance);
    }
    
    public static void closestToFirst() {
        Point firstPoint = points.get(0);
        Point closestPoint = points.get(1);
        double closestDistance = distanceBetweenPoints(firstPoint, closestPoint);
        if (points.size() > 2) {
            for (int i=2; i<points.size(); i++) {
                double currentDistance = distanceBetweenPoints(firstPoint, points.get(i));
                if (currentDistance < closestDistance) {
                    closestDistance = currentDistance;
                    closestPoint = points.get(i);
                }
            }
        }
        System.out.println("Closest to the first point ("+ firstPoint.x +","+ firstPoint.y +") is ("+ closestPoint.x +","+ closestPoint.y +") at a distance of " + closestDistance);
    }
    
    public static double distanceBetweenPoints(Point a, Point b) {
        /* 
         * using distance formula derived from the Pythagorean theorem: 
         * ((x2-x1)^2+(y2-y1)^2)^(1/2)
         * http://www.mathwarehouse.com/algebra/distance_formula/index.php
        */
        return  Math.sqrt(Math.pow((b.x - a.x),2) + Math.pow((b.y - a.y),2));
    }
}
