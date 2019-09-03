package sample.Frame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle implements Drawable {


    private double x, y, e;

    private double[] xPoints = {0,0,0}, yPoints = {0,0,0};
    private Color c;


    public Triangle(double x , double y, double edgeLength, Color c)
    {
        this.x = x;
        this.y = y;
        this.e = edgeLength;

        xPoints[0] = x;
        yPoints[0] = y;

        this.c = c;

    }

    // this exists so we can calculate the relative points of a triangle based on the initial bottom left part of the
    // triangle (x and y from constructor)
    public void calculatePoints() {


        //x and y coordinate for the top tip of the triangle
        yPoints[1] = y - e;
        xPoints[1] = x + (e/2);

        // x and y coordinate for the
        xPoints[2] = x + e;
        yPoints[2] = y;


    }

    @Override
    public void draw(Canvas canvas) {

        calculatePoints();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(c);
        gc.fillPolygon(xPoints, yPoints, 3);

    }

}
