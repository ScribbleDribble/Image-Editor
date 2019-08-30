package sample.Frame;

import java.util.ArrayDeque;

/***
 * Used to keep track of points that form shapes, between multiple function calls
 */
public class PointStorage {

    private ArrayDeque<Double> points = new ArrayDeque<>();

    // pairs of elements will form a point
    public void pushXY(double x, double y) {
        points.push(x);
        points.push(y);
    }

    public double pop() {
        return points.pop();
    }

    public int size() {
        return points.size();
    }

}
