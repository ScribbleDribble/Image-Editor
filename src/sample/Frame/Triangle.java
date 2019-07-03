package sample.Frame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Triangle implements Drawable {


    private double x, y, e, h;

    private double xPoints[] = {0,0,0}, yPoints[] = {0,0,0};

    public Triangle(double x , double y, double e)
    {
        this.x = x;
        this.y = y;
        this.e = e;

        xPoints[0] = x;
        yPoints[0] = y;

    }


    public void calculatePoints() {

        yPoints[1] = Math.sqrt(Math.pow(e, 2) - (Math.pow(e/2,2)));
        xPoints[1] = x + (e/2);
        xPoints[2] = x + e;
        yPoints[2] = y;


    }

    @Override
    public void draw(Canvas canvas) {

        calculatePoints();


        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillPolygon(xPoints, yPoints, 3);

    }



}
