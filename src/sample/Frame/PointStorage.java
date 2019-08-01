package sample.Frame;

import java.util.ArrayDeque;

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
